package crestedbutte.dom

import crestedbutte.Browser
import org.scalajs.dom.experimental.serviceworkers.toServiceWorkerNavigator
import zio.ZIO

import scala.util.{Failure, Success}

object ServiceWorkerBillding {

  def register(path: String) =
    ZIO
      .environment[Browser]
      .map {
        registerServiceWorkerLogic(_, path)
      }

  def registerServiceWorkerLogic(browser: Browser, path: String) = {
    // TODO Ew. Try to get this removed after first version of PWA is working
    import scala.concurrent.ExecutionContext.Implicits.global

    toServiceWorkerNavigator(browser.browser.window().navigator).serviceWorker
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
