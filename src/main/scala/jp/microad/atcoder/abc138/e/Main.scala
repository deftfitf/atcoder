package jp.microad.atcoder.abc138.e

object Main {

  import scala.collection.mutable.ArrayBuffer

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S, T = sc.nextLine()
    var IDX = Array.fill(26)(ArrayBuffer[Int]())

    var i = 0
    while (i < S.length) {
      IDX(S(i) - 'a').append(i)
      i += 1
    }

    def lowerBound(arr: IndexedSeq[Int], x: Int): Int = {
      def dfs(l: Int, r: Int): Int =
        if (r - l > 1) {
          val center = l + (r - l) / 2
          if (arr(center) <= x) dfs(center, r)
          else dfs(l, center)
        } else r
      dfs(-1, arr.length)
    }

    i = 0
    var round = 0
    var j = -1
    while (i < T.length) {
      val IDX_T = IDX(T(i) - 'a')
      var idxT = lowerBound(IDX_T, j)
      if (idxT == IDX_T.length) {
        if (j == -1) {
          println("-1")
          return
        }
        round += 1
        j = -1
      } else {
        j = IDX_T(idxT)
        i += 1
      }
    }
    println(round.toLong * S.length.toLong + (j.toLong + 1L))
  }

}
