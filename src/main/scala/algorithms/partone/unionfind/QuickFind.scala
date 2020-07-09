package algorithms.partone.unionfind

import scala.util.Try

case class QuickFind(input: Array[Int]) {

  def connected(e1: Int, e2: Int): Boolean = Try(input(e1) == input(e2)).getOrElse(false)

  def union(e1: Int, e2: Int): Unit = Try {
    val (e1Id, e2Id) = (input(e1), input(e2))

    if (e1Id != e2Id) {
      input.indices foreach { e =>
        if (input(e) == e1Id) input(e) = e2Id
      }
    }
  }

}
