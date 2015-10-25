package dtos

import java.util.UUID

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.{JsPath, Reads}


class CategoryDto(val id: UUID, val name: String, val description: Option[String], val image: Option[String]) {

  def this(name: String, description: Option[String], image: Option[String]) {
    this(null, name, description, image)
  }
}


object CategoryDto {

  implicit val categoryCreateReads: Reads[CategoryDto] = (
        (JsPath \ "name").read[String](maxLength[String](100) keepAnd minLength[String](2)) and
        (JsPath \ "description").readNullable[String](maxLength[String](255)) and
        (JsPath \ "image").readNullable[String](maxLength[String](255))
      )((name: String, description: Option[String], image: Option[String]) => new CategoryDto(name, description, image))

  implicit val categoryUpdateReads: Reads[CategoryDto] = (
    (JsPath \ "id").read[UUID] and
      (JsPath \ "name").read[String](maxLength[String](100)) and
      (JsPath \ "description").readNullable[String](maxLength[String](255)) and
      (JsPath \ "image").readNullable[String](maxLength[String](255))
    ).apply((id: UUID, name: String, description: Option[String], image: Option[String]) => new CategoryDto(id, name, description, image))
}
