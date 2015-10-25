package controllers

import play.api.mvc._

class Application extends Controller with ApiResponse {

  def index = Action {
    okResponse()
  }

}
