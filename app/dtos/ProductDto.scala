package dtos

import java.util.UUID

import play.api.libs.json.{Json, JsPath, Reads}
import play.api.libs.functional.syntax._

case class ProductDto (id: Option[UUID], name: String, description: Option[String],
                       image: Option[String], categoryId: Option[UUID], unitId: Option[UUID])

object ProductDto {
  implicit val productReads: Reads[ProductDto] = (
      (JsPath \ "id").readNullable[UUID] and
      (JsPath \ "name").read[String](Reads.maxLength[String](100)) and
      (JsPath \ "description").readNullable[String](Reads.maxLength[String](255)) and
      (JsPath \ "image").readNullable[String](Reads.maxLength[String](255)) and
      (JsPath \ "categoryId").readNullable[UUID] and
      (JsPath \ "unitId").readNullable[UUID]
    )(ProductDto.apply _)
  implicit val productWrites = Json.writes[ProductDto]
}