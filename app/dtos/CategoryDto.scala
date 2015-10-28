package dtos

import java.util.UUID

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.{JsPath, Reads, Writes}


class CategoryDto(val id: UUID, val name: String, val description: Option[String], val image: Option[String]) {

  def this(name: String, description: Option[String], image: Option[String]) {
    this(null, name, description, image)
  }
}


object CategoryDto {

  def extractor(dto: CategoryDto): Option[(UUID, String, Option[String], Option[String])] =
    Some(dto.id, dto.name, dto.description, dto.image)

  implicit val categoryDtoReads: Reads[CategoryDto] = (
        (JsPath \ "name").read[String](maxLength[String](100) keepAnd minLength[String](2)) and
        (JsPath \ "description").readNullable[String](maxLength[String](255)) and
        (JsPath \ "image").readNullable[String](maxLength[String](255))
      )((name: String, description: Option[String], image: Option[String]) => new CategoryDto(name, description, image))

  implicit val categoryWrites: Writes[CategoryDto] = (
      (JsPath \ "id").write[UUID] and
      (JsPath \ "name").write[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "image").writeNullable[String])(unlift(extractor))
}
