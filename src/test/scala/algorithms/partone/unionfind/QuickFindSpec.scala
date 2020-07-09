package algorithms.partone.unionfind

import algorithms.load.FileReader
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class QuickFindSpec extends AnyWordSpec with Matchers {

  "QuickFind connected" should {

    val ID: Array[Int]       = 0 to FileReader.dataSource.map { case (k, v) => if (k < v) v else k }.max toArray
    val quickFind: QuickFind = QuickFind(ID)

    FileReader.dataSource.foreach { case (e1, e2) => quickFind.union(e1, e2) }

    "return true for 3 and 4" in {
      val (e1, e2) = 3 -> 4
      quickFind.connected(e1, e2) shouldBe true
    }

    "return false for 1 and 100" in {
      val (e1, e2) = 1 -> 100
      quickFind.connected(e1, e2) shouldBe false
    }

  }

}
