package ar.edu.austral.fi.concurrency.actor.crawler

import org.asynchttpclient.DefaultAsyncHttpClient

import java.util.concurrent.Executor
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future, Promise}

trait WebClient {
  def get(url: String)(implicit exec: Executor): Future[String]
}

case class BadStatus(status: Int) extends RuntimeException

object AsyncWebClient extends WebClient {

  private val client = new DefaultAsyncHttpClient()

  def get(url: String)(implicit exec: Executor): Future[String] = {
    val f = client.prepareGet(url).execute();
    val p = Promise[String]()
    f.addListener(new Runnable {
      def run(): Unit = {
        val response = f.get
        if (response.getStatusCode / 100 < 4)
          p.success(response.getResponseBody)
        else p.failure(BadStatus(response.getStatusCode))
      }
    }, exec)
    p.future
  }

  def shutdown(): Unit = client.close()

}

object WebClientTest extends App {
  implicit val executor: ExecutionContextExecutor = ExecutionContext.global
  AsyncWebClient.get("http://www.google.com/")
    .map(println)
    .andThen { case _ => AsyncWebClient.shutdown() }
}
