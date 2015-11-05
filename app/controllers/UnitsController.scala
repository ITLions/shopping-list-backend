package controllers

import java.sql.Timestamp
import java.util.UUID

import com.google.inject.Inject
import dtos.UnitDto
import models.Tables._
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.{JsError, Json}
import play.api.mvc.{Action, BodyParsers, Controller}
import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future


class UnitsController @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller with ApiResponse {

  val db = dbConfigProvider.get[JdbcProfile].db

  def create = Action.async(BodyParsers.parse.json) { request =>
    request.body.validate[UnitDto].fold({ errors =>
      Future {
        badRequestResponse("Validation failed", JsError.toJson(errors))
      }
    }, { dto =>
      val unit = new UnitsRow(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()),
        new Timestamp(System.currentTimeMillis()), dto.name)
      db.run(Units += unit).map { result => okResponse() }
    })
  }

  def update(id: UUID) = Action.async(BodyParsers.parse.json) { request =>
    request.body.validate[UnitDto].fold(
      errors => {
        Future {
          badRequestResponse("Validation failed", JsError.toJson(errors))
        }
      },
      dto => {
        db.run(Units.filter(unit => unit.id === id)
          .map(unit => (unit.name, unit.updatedDate))
          .update(dto.name, new Timestamp(System.currentTimeMillis())))
          .map({ case 1 => okResponse() case _ => badRequestResponse() })
      }
    )
  }

  def delete(id: UUID) = Action.async { request =>
    db.run(Units.filter(unit => unit.id === id).delete)
      .map({ case 1 => okResponse() case _ => badRequestResponse() })
  }

  def list(offset: Long, limit: Long) = Action.async { request =>
    db.run(Units.drop(offset).take(limit)
      .map(unit => (unit.id, unit.name)).result).map { resultSet =>
      val dtos = resultSet.map(row => new UnitDto(Option(row._1), row._2))
      listResponse(dtos.length, Json.toJson(dtos))
    }
  }
}
