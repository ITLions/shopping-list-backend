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
      errors => {
        Future {
          badRequestResponse("Validation failed", JsError.toJson(errors))
        }
      },
      categoryDto => {
        val category = new CategoriesRow(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()),
          new Timestamp(System.currentTimeMillis()), categoryDto.name, categoryDto.description, categoryDto.image)
        db.run(Categories += category).map { pi => okResponse() }
      }
    )
  }

  def update = Action.async(BodyParsers.parse.json) {request =>
    request.body.validate[CategoryDto].fold(
      errors => {
        Future {
          badRequestResponse("Validation failed", JsError.toJson(errors))
        }
      },
      categoryDto => {
        Future {
          okResponse()
        }
      }
    )
  }
}
