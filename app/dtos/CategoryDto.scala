package dtos

import java.util.UUID

import play.api.libs.json.{Json, JsPath, Reads}
import play.api.libs.functional.syntax._


case class CategoryDto(id: Option[UUID], name: String, description: Option[String], image: Option[String])

object CategoryDto {
  implicit val categoryReads: Reads[CategoryDto] = (
      (JsPath \ "id").readNullable[UUID] and
      (JsPath \ "name").read[String](Reads.maxLength[String](100)) and
      (JsPath \ "description").readNullable[String](Reads.maxLength[String](255)) and
      (JsPath \ "image").readNullable[String](Reads.maxLength[String](255))
    )(CategoryDto.apply _)
  implicit val categoryDtoWrites = Json.writes[CategoryDto]
}
