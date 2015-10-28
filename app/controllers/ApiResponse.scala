package controllers

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Results

case class Metadata(code: Int, message: String)

case class ListData(totalSize: Int, items: JsValue)

case class Response(metadata: Metadata, content: Option[JsValue])

trait ApiResponse {

  implicit val metadataWrites = Json.format[Metadata]
  implicit val listDataFormat = Json.format[ListData]
  implicit val responseWrites = Json.format[Response]

  def okResponse(content: JsValue = null) = Results.Ok(Json.toJson(Response(Metadata(200, "Ok"), Option.apply(content))))

  def badRequestResponse(message: String = null, content: JsValue = null) = Results
    .BadRequest(Json.toJson(Response(Metadata(400, Option.apply(message).getOrElse("Bad Request")), Option.apply(content))))

  def listResponse(count: Int, items: JsValue) = {
    val listData = Json.toJson(ListData(count, items))
    Results.Ok(Json.toJson(Response(Metadata(200, "Ok"), Option(listData))))
  }
}
