package algorithms.load

import scala.io.Source

object FileReader {

  def dataSource: Iterator[(Int, Int)] =
    Source
      .fromResource("unionfind.txt")
      .getLines
      .map(
        _.split(" ")
          .map(_.toInt)
          .toList
      )
      .map { case a :: b :: _ =>
        (a, b)
      }

}
