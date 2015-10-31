package controllers

import java.sql.Timestamp
import java.util.UUID

import com.google.inject.Inject
import dtos.CategoryDto
import models.Tables._
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.mvc.{Action, BodyParsers, Controller}
import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._

import scala.concurrent.Future

class CategoriesController @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller with ApiResponse {

  val db = dbConfigProvider.get[JdbcProfile].db

  def create = Action.async(BodyParsers.parse.json) { request =>
    request.body.validate[CategoryDto].fold(
      errors => { Future {
          badRequestResponse("Validation failed", JsError.toJson(errors))
        }},
      dto => {
        val category = new CategoriesRow(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()),
          new Timestamp(System.currentTimeMillis()), dto.name, dto.description, dto.image)
        db.run(Categories += category).map { result => okResponse() }
      }
    )
  }

  def update(id: UUID) = Action.async(BodyParsers.parse.json) { request =>
    request.body.validate[CategoryDto].fold(
      errors => { Future {
          badRequestResponse("Validation failed", JsError.toJson(errors))
        }},
      dto => {
        db.run(Categories.filter(category => category.id === id)
          .map(category => (category.name, category.description, category.icon, category.updatedDate))
          .update(dto.name, dto.description, dto.image, new Timestamp(System.currentTimeMillis())))
          .map({ case 1 => okResponse() case _ => badRequestResponse() })
      }
    )
  }

  def delete(id: UUID) = Action.async { request =>
      db.run(Categories.filter(category=>category.id === id).delete)
        .map({ case 1 => okResponse() case _ => badRequestResponse() })
  }

  def list(offset: Long, limit: Long) = Action.async { request =>
    db.run(Categories.drop(offset).take(limit)
      .map(category => (category.id, category.name, category.description, category.icon)).result).map { resultSet =>
      val dtos = resultSet.map(row => new CategoryDto(row._1, row._2, row._3, row._4))
      listResponse(dtos.length, Json.toJson(dtos))
    }
  }
}
