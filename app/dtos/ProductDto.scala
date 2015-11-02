package dtos

import java.util.UUID

import play.api.libs.json.Reads._
import play.api.libs.json.{Writes, Json, JsPath, Reads}
import play.api.libs.functional.syntax._

case class ProductDto (id: UUID, name: String, description: Option[String],
                       image: String, categoryId: UUID, unitId: UUID) {

  def this(name: String, description: Option[String], image: String,
           categoryId: UUID, unitId: UUID){
    this(null, name, description,image, categoryId, unitId)
  }
}

object ProductDto {

  def extractor(dto: ProductDto): Option[(UUID, String, Option[String], String, UUID, UUID)] =
    Some(dto.id, dto.name, dto.description, dto.image, dto.categoryId, dto.unitId)

  implicit val productDtoReads: Reads[ProductDto] = (
      (JsPath \ "name").read[String](maxLength[String](100) keepAnd minLength[String](2)) and
      (JsPath \ "description").readNullable[String](maxLength[String](255)) and
      (JsPath \ "image").read[String](maxLength[String](255)) and
      (JsPath \ "categoryId").read[UUID] and
      (JsPath \ "unitId").read[UUID]
    )((name: String, description: Option[String], image: String, categoryId: UUID, unitID: UUID)
    => new ProductDto(name, description, image, categoryId, unitID))

  implicit val productDtoWrites: Writes[ProductDto] = (
      (JsPath \ "id").write[UUID] and
      (JsPath \ "name").write[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "image").write[String] and
      (JsPath \ "categoryId").write[UUID] and
      (JsPath \ "unitId").write[UUID])(unlift(extractor))

}