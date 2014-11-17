/**
 * Copyright (C) 2014, George B. Norr, All Rights Reserved
 * Date: 11/2/14
 */
package controllers.api

import models.core.Item
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object ItemController extends Controller {
  def list = Action {
    Ok(Json.toJson(Item.list()))
  }

  val create = Action {NotImplemented}

  def details(id: Int) = Action {NotImplemented}

  def update(id: Int) = Action {NotImplemented}

  def delete(id: Int) = Action {NotImplemented}
}
