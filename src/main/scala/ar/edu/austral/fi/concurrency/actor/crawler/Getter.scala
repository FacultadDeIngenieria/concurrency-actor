package ar.edu.austral.fi.concurrency.actor.crawler

import akka.actor.{Actor, Status}
import akka.pattern.pipe
import org.jsoup.Jsoup

import java.util.concurrent.Executor
import scala.concurrent.ExecutionContext
import scala.jdk.CollectionConverters._

class Getter(url: String, depth: Int) extends Actor {

  implicit val executor: Executor with ExecutionContext = context.dispatcher.asInstanceOf[Executor with ExecutionContext]
  def client: WebClient = AsyncWebClient

  client get url pipeTo self

  def receive: Receive = {
    case body: String =>
      ???
    case _: Status.Failure =>
      context.stop(self)
  }
  
  def findLinks(body: String): Iterator[String] = {
    val document = Jsoup.parse(body, url)
    val links = document.select("a[href]")
    for {
      link <- links.iterator().asScala
    } yield link.absUrl("href")
  }

}