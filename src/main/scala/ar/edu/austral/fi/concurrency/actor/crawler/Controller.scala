package ar.edu.austral.fi.concurrency.actor.crawler

import akka.actor.{Actor, ActorLogging, OneForOneStrategy, Props, ReceiveTimeout, SupervisorStrategy, Terminated}

import scala.concurrent.duration._

class Controller extends Actor with ActorLogging {
  import Controller._

  def getterProps(url: String, depth: Int): Props = Props(new Getter(url, depth))

  def receive: Receive = {
    case Check(url, depth) => ???
    case Terminated(_) => ???
    case ReceiveTimeout => ???
  }

}
object Controller {
  case class Check(url: String, depth: Int)
  case class Result(links: Set[String])
}
