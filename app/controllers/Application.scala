package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.main("It's work!"))
  }

}