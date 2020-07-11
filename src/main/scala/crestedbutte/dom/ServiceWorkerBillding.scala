package crestedbutte.dom

import crestedbutte.Browser
import org.scalajs.dom.experimental.serviceworkers.toServiceWorkerNavigator
import zio.ZIO

import scala.util.{Failure, Success}

object ServiceWorkerBillding {

  def register(path: String) =
    ZIO
      .environment[Browser.Service]
      .map {
        registerServiceWorkerLogic(_, path)
      }

  def registerServiceWorkerLogic(browser: Browser.Service, path: String) = {
    // TODO Ew. Try to get this removed after first version of PWA is working
    import scala.concurrent.ExecutionContext.Implicits.global

    toServiceWorkerNavigator(browser.window().navigator).serviceWorker
      .register(path)
      .toFuture
      .onComplete {
        case Success(registration) =>
          registration.update()
        case Failure(error) =>
          println(
            s"registerServiceWorker: service worker registration failed > ${error.printStackTrace()}",
          )
      }
  }
}
