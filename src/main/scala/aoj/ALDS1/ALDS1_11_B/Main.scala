package aoj.ALDS1.ALDS1_11_B

import scala.collection.mutable.ArrayBuffer

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    // 深さ優先探索では, 各頂点に以下のタイムスタンプを押す
    // d[v]: vを最初に発見した発見時刻を記録
    // f[v]: vの隣接リストを調べ終えた完了時刻を記録する
    // Gは隣接リスト表現, 各頂点には1からnまでの番号が振られている
    // 各隣接リストの頂点は番号が小さい順に並べられている
    // 各頂点の発見時刻と完了時刻を報告する
    // 深さ優先探索の仮定において, 訪問する頂点の候補が複数ある場合, 頂点番号が小さいものから選択する
    // 最初に訪問する頂点の開始時刻を1とする
    val V = sc.nextInt()
    val G = Array.fill(V+1)(new ArrayBuffer[Int])
    for (v <- 1 to V) {
      val _, k = sc.nextInt()
      for (_ <- 0 until k) {
        val u = sc.nextInt()
        G(v).append(u)
      }
    }

    def dfs(V: Int, G: Array[ArrayBuffer[Int]]): (Array[(Int, Int)]) = {
      val d = new Array[Int](V)
      val f = new Array[Int](V)
      @scala.annotation.tailrec
      def recursive(stack: List[Int], timestamp: Int): Int =
        if (stack.nonEmpty) {
          val u = stack.head
          if (u < 0) {
            f(-u-1) = timestamp
            recursive(stack.tail, timestamp + 1)
          } else if (d(u-1) == 0) {
            d(u-1) = timestamp
            recursive(G(u).toList ::: (-u :: stack.tail), timestamp + 1)
          } else recursive(stack.tail, timestamp)
        } else timestamp
      (1 to V).foldLeft(1)((timestamp, v) => recursive(v :: Nil, timestamp))
      d.zip(f)
    }

    val r = dfs(V, G).zipWithIndex.map { case ((d, f), i) => s"${i+1} $d $f" }. mkString("\n")
    println(r)
  }

}
