/**
 * Copyright (C) 2014, George B. Norr, All Rights Reserved
 * Date: 11/3/14
 */
package controllers.web

import play.api.mvc.{Action, Controller}

object WebController extends Controller {
  def index = Action {
    Ok(views.html.web.index())
  }
}
