package ar.edu.austral.fi.concurrency.actor.counter

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class CounterSpec()
  extends TestKit(ActorSystem("CounterSpec"))
    with ImplicitSender
    with AnyWordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  "A Counter actor" must {

    "increment and get counts" in {
      val counter = system.actorOf(Props[Counter]())
      counter ! "hello world"
      expectMsg("hello world")
    }

  }

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

}
