package controllers

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Results

case class Metadata(code: Int, message: String)

case class Response(metadata: Metadata, content: Option[JsValue])

trait ApiResponse {

  implicit val metadataWrites = Json.writes[Metadata]
  implicit val metadataReads = Json.reads[Metadata]

  implicit val responseWrites = Json.writes[Response]
  implicit val responseReads = Json.reads[Response]


  def okResponse(content: JsValue = null) = Results.Ok(Json.toJson(Response(Metadata(200, "Ok"), Option.apply(content))))


  def badRequestResponse(message: String = null, content: JsValue = null) = Results
    .BadRequest(Json.toJson(Response(Metadata(400, Option.apply(message).getOrElse("Bad Request")), Option.apply(content))))

}
