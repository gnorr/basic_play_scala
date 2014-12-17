/**
 * Copyright (C) 2014, George B. Norr, All Rights Reserved
 * Date: 11/2/14
 */
package controllers.api

import org.specs2.execute.AsResult
import play.api.http.{MimeTypes, Writeable}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AnyContentAsEmpty, Call, Result}
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

import scala.concurrent.Future

/**
 * Some of the ideas found here are from:
 * https://github.com/julienrf/pfe-samples/blob/master/pfe-scala/test/controllers/ItemsSpec.scala
 */
class ItemControllerSpec extends PlaySpecification {
  "ItemController" should {
    "list items" in new WithApplication() {
      successfullyRoute(emptyJsonRequest(controllers.api.routes.ItemController.list())) { response =>
        status(response) must equalTo(OK)
        contentAsJson(response) must equalTo(Json.arr())
      }
    }

    "create/update/delete an item" in new WithApplication() {
      val createItem = Json.obj("name" -> "test item")

      successfullyRoute(jsonRequest(controllers.api.routes.ItemController.create(), createItem)) { createdResponse =>
        status(createdResponse) must equalTo(OK)
        val newItem = contentAsJson(createdResponse)
        val newItemId = (newItem \ "id").as[Int]
        (newItem \ "name").as[String] must equalTo("test item")
        newItemId must greaterThan(0)

        val updateItem = Json.obj("id" -> newItemId, "name" -> "test item update")
        successfullyRoute(jsonRequest(controllers.api.routes.ItemController.update(newItemId), updateItem)) { updateResponse =>
          (contentAsJson(updateResponse) \ "name").as[String] must equalTo("test item update")
        }

        successfullyRoute(emptyJsonRequest(controllers.api.routes.ItemController.details(newItemId))) { detailsResponse =>
          (contentAsJson(detailsResponse) \ "name").as[String] must equalTo("test item update")
        }

        successfullyRoute(emptyJsonRequest(controllers.api.routes.ItemController.delete(newItemId))) { deleteResponse =>
          status(deleteResponse) must equalTo(OK)
        }

        successfullyRoute(emptyJsonRequest(controllers.api.routes.ItemController.details(newItemId))) { detailsResponse =>
          status(detailsResponse) must equalTo(NOT_FOUND)
        }
      }
    }

    def successfullyRoute[A: Writeable, B: AsResult](request: FakeRequest[A])(f: Future[Result] => B) =
      route(request) must beSome(f)

    def jsonRequest(call: Call, body: JsValue): FakeRequest[JsValue] =
      FakeRequest(call).withBody(body).withHeaders(ACCEPT -> MimeTypes.JSON)

    def emptyJsonRequest(call: Call): FakeRequest[AnyContentAsEmpty.type] =
      FakeRequest(call).withHeaders(ACCEPT -> MimeTypes.JSON)
  }
}
