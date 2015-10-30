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


  def delete(id: UUID) = Action.async { request =>
    db.run(Products.filter(c=>c.id === id).delete).map({ case 1 => okResponse() case _ => badRequestResponse() })
  }

  def list(offset: Long, limit: Long) = Action.async { request =>
    db.run(Products.countDistinct.result) flatMap { count =>
      db.run(Products.drop(offset).take(limit).map(c => (c.id, c.name, c.description, c.icon, c.categoryId, c.unitId)).result).map {
        resultSet =>
          val dtos = resultSet.map(row => new ProductDto(row._1, row._2, row._3, row._4, row._5, row._6))
          listResponse(count, Json.toJson(dtos))
      }
    }
  }
}
