/**
 * Copyright (C) 2014, George B. Norr, All Rights Reserved
 * Date: 11/2/14
 */
package controllers.api

import models.core.{CreateItem, Item}
import models.core.Item._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object ItemController extends Controller {
  def list = Action {
    Ok(Json.toJson(Item.list()))
  }

  val create = Action(parse.json[CreateItem]) { implicit request =>
    Item.create(request.body.name) match {
      case Some(item) => Ok(Json.toJson(item))
      case None => InternalServerError
    }
  }

  def details(id: Int) = Action {
    Item.get(id) match {
      case Some(item) => Ok(Json.toJson(item))
      case None => NotFound
    }
  }

  def update(id: Int) = Action(parse.json[Item]) {implicit request =>
    Item.update(request.body.id, request.body.name) match {
      case Some(item) => Ok(Json.toJson(item))
      case None => InternalServerError
    }
  }

  def delete(id: Int) = Action {
    if(Item.delete(id))
      Ok("")
    else
      NotFound
  }
}
