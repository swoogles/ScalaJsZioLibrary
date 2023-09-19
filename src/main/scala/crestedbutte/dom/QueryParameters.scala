package crestedbutte

import zio.ZIO

object QueryParameters {

  def getRequired[T](
    parameterName: String,
    typer: String => T,
  ): ZIO[Browser.Service, Nothing, Option[T]] =
    getOptional(parameterName, raw => Some(typer(raw)))

  def getOptional[T](parameterName: String,
                     typer: String => Option[T]): ZIO[Browser.Service, Nothing, Option[T]] =
    ZIO
      .service[Browser.Service]
      .map(
        browser =>
          UrlParsing
            .getUrlParameter(
              browser.window().location.toString,
              parameterName,
            )
            .flatMap(typer),
      )

  def getOptional[T](parameterName: String): ZIO[Browser.Service, Nothing, Option[String]] =
    getOptional(parameterName, x => Some(x))

  def getOptionalZ[T](
    parameterName: String,
    typer: String => Option[T],
  ): ZIO[Browser.Service, Unit, T] =
    ZIO
      .service[Browser.Service]
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
      .flatMap(optResult => ZIO.fromOption(optResult)).mapError( _ => ())
}
