package ar.edu.austral.fi.concurrency.actor.crawler

import akka.actor.{Actor, ActorRef, Props}

class Receptionist extends Actor {
  import Receptionist._

  def controllerProps: Props = Props[Controller]

  var reqNo = 0

  def receive: Receive = waiting

  val waiting: Receive = {
    case Get(url) => ???
  }

}

object Receptionist {
  private case class Job(client: ActorRef, url: String)
  case class Get(url: String)
  case class Result(url: String, links: Set[String])
  case class Failed(url: String, reason: String)
}
