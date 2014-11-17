/**
 * Copyright (C) 2014, George B. Norr, All Rights Reserved
 * Date: 11/3/14
 */
package controllers.web

import play.api.test.{WithBrowser, PlaySpecification}

class WebControllerSpec extends PlaySpecification{
  "WebController" should {
    "render the index page" in new WithBrowser {
      browser.goTo(controllers.web.routes.WebController.index().url)

      browser.pageSource() must contain("droids")
    }
  }
}
