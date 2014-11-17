/**
 * Copyright (C) 2014, George B. Norr, All Rights Reserved
 * Date: 11/2/14
 */
package models.core

import org.specs2.mutable.Specification

class ItemSpec extends Specification {
  "Item" should {
    "add items" in {
      Item.create("test item") must beSome[Item].which {
        item => item.name == "test item" && item.id > 0
      }
    }
  }
}
