package jp.microad.atcoder.abc126.d

object Main {

  case class Edge(v: Int, cost: Int)

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val N = sc.nextInt()
    val G = Array.fill(N)(new scala.collection.mutable.ArrayBuffer[Edge]())
    (0 until (N - 1)) foreach { _ =>
      val u, v, w = sc.nextInt()
      G(u-1).append(Edge(v-1, w))
      G(v-1).append(Edge(u-1, w))
    }

    // ある頂点をある色で塗ったとき,
    // そこから偶数距離の頂点は全て同じ色になる
    // そうではない色は同じ色にはならない
    def solve(): String = {
      val color = new Array[Int](N) // 1, -1, 0の時、色を塗っていないので訪れてもいない

      color(0) = 1
      def dfs(u: Int, acm: Long): Unit = {
        G(u) foreach { edge =>
          if (color(edge.v) == 0) {
            if (acm + edge.cost % 2 == 0)
              color(edge.v) = color(0)
            else
              color(edge.v) = -color(0)
            dfs(edge.v, acm + edge.cost)
          }
        }
      }
      dfs(0, 0)
      color.map { c =>
        if (c == -1) 0
        else 1
      }.mkString("\n")
    }
    println(solve())
  }

}
