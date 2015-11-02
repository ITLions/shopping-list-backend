package controllers

import java.sql.Timestamp
import java.util.UUID

import com.google.inject.Inject
import dtos.ProductDto
import models.Tables._
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.mvc.{Action, BodyParsers, Controller}
import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._

import scala.concurrent.Future

class ProductsController @Inject() (dbConfigProvider: DatabaseConfigProvider) extends Controller with ApiResponse {

  val db = dbConfigProvider.get[JdbcProfile].db

  def create = Action.async(BodyParsers.parse.json) { request =>
    request.body.validate[ProductDto].fold(
      errors => { Future {
        badRequestResponse("Validation failed", JsError.toJson(errors))
      }},
    dto => {
      val product = new ProductsRow(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()),
        new Timestamp(System.currentTimeMillis()), dto.name, dto.description, dto.image, dto.categoryId, dto.unitId)
      db.run(Products += product).map{ pi => okResponse()}
    })
  }

  def update(id: UUID) = Action.async(BodyParsers.parse.json) { request =>
    request.body.validate[ProductDto].fold(
      errors => { Future {
          badRequestResponse("Validation failed", JsError.toJson(errors))
        }},
      dto => {
        db.run(Products.filter(product => product.id === id)
          .map(product => (product.name, product.description, product.icon, product.updatedDate,
            product.categoryId, product.unitId))
          .update((dto.name, dto.description, dto.image, new Timestamp(System.currentTimeMillis()),
            dto.categoryId, dto.unitId))
          .map({ case 1 => okResponse() case _ => badRequestResponse() }))
      }
    )
  }

  def delete(id: UUID) = Action.async { request =>
    db.run(Products.filter(product => product.id === id).delete)
      .map({ case 1 => okResponse() case _ => badRequestResponse() })
  }

  def list(offset: Long, limit: Long) = Action.async { request =>
    db.run(Products.drop(offset).take(limit)
      .map(product => (product.id, product.name, product.description,
        product.icon, product.categoryId, product.unitId)).result).map { resultSet =>
      val dtos = resultSet.map(row => new ProductDto(row._1, row._2, row._3, row._4, row._5, row._6))
      listResponse(dtos.length, Json.toJson(dtos))
    }
  }
}
