package dtos

import java.util.UUID

import play.api.libs.json.Json


case class UnitDto(id: Option[UUID], name: String)

object UnitDto {
  implicit val unitFormat = Json.format[UnitDto]
}
