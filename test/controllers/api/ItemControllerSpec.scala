/**
 * Copyright (C) 2014, George B. Norr, All Rights Reserved
 * Date: 11/2/14
 */
package controllers.api

import play.api.libs.json.Json
import play.api.test._

class ItemControllerSpec extends PlaySpecification {
  "ItemController" should {
    "list items" in new WithApplication() {
      route(FakeRequest(controllers.api.routes.ItemController.list())) match {
        case Some(response) => {
          status(response) must equalTo(OK)
          contentAsJson(response) must equalTo(Json.arr())
        }
        case None => failure
      }
    }
  }
}
