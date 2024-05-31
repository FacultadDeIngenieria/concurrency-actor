package ar.edu.austral.fi.concurrency.actor.crawler

import akka.actor.{Actor, ActorSystem, Props, ReceiveTimeout}
import ar.edu.austral.fi.concurrency.actor.crawler.Receptionist.{Failed, Get, Result}

import scala.concurrent.duration._

class Main extends Actor {

  private val receptionist = context.actorOf(Props[Receptionist], "receptionist")
  context.watch(receptionist) // Death pact

  receptionist ! Get("http://www.google.com")

  context.setReceiveTimeout(10.seconds)

  def receive: Receive = {
    case Result(url, set) =>
      println(set.toVector.sorted.mkString(s"Results for '$url':\n", "\n", "\n"))
    case Failed(url, reason) =>
      println(s"Failed to fetch '$url': $reason\n")
    case ReceiveTimeout =>
      context.stop(self)
  }

  override def postStop(): Unit = {
    AsyncWebClient.shutdown()
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("main")
    system.actorOf(Props[Main], "main")
  }
}