package ar.edu.austral.fi.concurrency.actor.counter

import akka.actor.Actor

class Counter extends Actor {
  var count = 0

  def receive: Receive = {
    case "incr" => count += 1
    case "get" => sender() ! count
  }
}
