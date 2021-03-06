/**
 * Copyright (C) 2014, George B. Norr, All Rights Reserved
 * Date: 11/2/14
 */
package models.core

import java.util.concurrent.atomic.AtomicInteger

import play.api.libs.json.Json

import scala.collection.concurrent.TrieMap

case class Item(id: Int, name: String)
case class CreateItem(name: String)

object Item {
  implicit val writesItem = Json.writes[Item]
  implicit val readsItem = Json.reads[Item]
  implicit val readsCreateItem = Json.reads[CreateItem]

  private val items = TrieMap.empty[Int, Item]
  private val seq = new AtomicInteger()

  //items.put(1, Item(1, "test item"))
  //seq.incrementAndGet()

  def list(): Seq[Item] = items.values.to[Seq]

  def create(name: String): Option[Item] = {
    val id = seq.incrementAndGet()
    val item = Item(id, name)
    items.put(id, item)
    Some(item)
  }

  def get(id: Int): Option[Item] = items.get(id)

  def update(id: Int, name: String): Option[Item] = {
    val item = Item(id, name)
    if(items.replace(id, item).isDefined)
      Some(item)
    else
      None
  }

  def delete(id: Int): Boolean = items.remove(id).isDefined

}
