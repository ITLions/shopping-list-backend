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
    request.body.validate[CategoryDto](CategoryDto.categoryCreateReads).fold(
      errors => {
        Future {
          badRequestResponse("Validation failed", JsError.toJson(errors))
        }
      },
      dto => {
        val category = new CategoriesRow(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()),
          new Timestamp(System.currentTimeMillis()), dto.name, dto.description, dto.image)
        db.run(Categories += category).map { pi => okResponse() }
      }
    )
  }

  def update = Action.async(BodyParsers.parse.json) { request =>
    request.body.validate[CategoryDto](CategoryDto.categoryUpdateReads).fold(
      errors => {
        Future {
          badRequestResponse("Validation failed", JsError.toJson(errors))
        }
      },
      dto => {
        db.run(Categories.filter(c => c.id === dto.id).map(c => (c.name, c.description, c.icon, c.updatedDate))
          .update(dto.name, dto.description, dto.image, new Timestamp(System.currentTimeMillis())))
          .map(i => okResponse(JsNumber(i)))
      }
    )
  }
}
