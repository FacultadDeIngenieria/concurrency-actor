package ar.edu.austral.fi.concurrency.actor.counter

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class CounterMain extends Actor {
  val counter: ActorRef = context.actorOf(Props[Counter], "counter")

  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  def receive: Receive = {
    case count: Int =>
      println(s"Count was $count")
      context.system.terminate()
  }
}

object CounterMain {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("main")
    system.actorOf(Props[CounterMain])
  }
}