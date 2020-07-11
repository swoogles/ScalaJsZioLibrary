package crestedbutte

import zio.{Has, ZIO}

object QueryParameters {

  def getRequired[T](
    parameterName: String,
    typer: String => T,
  ): ZIO[Has[Browser.Service], Nothing, Option[T]] =
    getOptional(parameterName, raw => Some(typer(raw)))

  def getOptional[T](parameterName: String,
                     typer: String => Option[T]): ZIO[Has[Browser.Service], Nothing, Option[T]] =
    ZIO
      .access[Has[Browser.Service]](_.get)
      .map(
        browser =>
          UrlParsing
            .getUrlParameter(
              browser.window().location.toString,
              parameterName,
            )
            .flatMap(typer),
      )

  def getOptional[T](parameterName: String): ZIO[Has[Browser.Service], Nothing, Option[String]] =
    getOptional(parameterName, x => Some(x))

  def getOptionalZ[T](
    parameterName: String,
    typer: String => Option[T],
  ): ZIO[Has[Browser.Service], Unit, T] =
    ZIO
      .access[Has[Browser.Service]](_.get)
      .map { browser =>
        val result: Option[T] =
          UrlParsing
            .getUrlParameter(
              browser.window().location.toString,
              parameterName,
            )
            .flatMap(typer)
        result
      }
      .flatMap(optResult => ZIO.fromOption(optResult))
}
