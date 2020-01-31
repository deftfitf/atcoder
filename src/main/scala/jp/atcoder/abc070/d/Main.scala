package jp.atcoder.abc070.d

import scala.collection.mutable

object Main {

  case class Edge(v: Int, w: Long)

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val INF = Long.MaxValue
    val V = sc.nextInt()
    val G = Array.fill(V + 1)(List[Edge]())
    (0 until V - 1) foreach { _ =>
      val u, v, w = sc.nextInt()
      G(u) ::= Edge(v, w)
      G(v) ::= Edge(u, w)
    }
    val Q, K = sc.nextInt()
    val X, Y = new Array[Int](Q)
    (0 until Q) foreach { i =>
      val x, y = sc.nextInt()
      X(i) = x
      Y(i) = y
    }

    // j番目の質問クエリ、頂点xjから頂点Kを経由しつつ、頂点yjまで
    // 移動する場合の最短経路の距離を求めてください
    // ...つまり, xjからKへの最短距離, Kからyjへの最短距離の和を答えればそれが最短経路となる
    // 木に対する全点対最短路問題を事前に解いておき, クエリへ回答する
    // 木かつ ci >= 1 なので, ダイクストラ法によって全点対最短路を求めることができる.

    def dijkstra(s: Int, V: Int, E: Int, G: Array[List[Edge]]): Array[Long] = {
      val d = Array.fill[Long](V+1)(INF)
      val queue = mutable.PriorityQueue[(Long, Int)]()(Ordering.by(_._1))
      d(s) = 0L
      queue.enqueue((0L, s))

      def loop(): Unit =
        if (queue.nonEmpty) {
          val (ud, u) = queue.dequeue()
          if (d(u) < ud) loop()
          else {
            d(u) = ud
            G(u) foreach { edge =>
              if (d(edge.v) > d(u) + edge.w) {
                d(edge.v) = d(u) + edge.w
                queue.enqueue((d(edge.v), edge.v))
              }
            }
            loop()
          }
        }
      loop()
      d
    }
//
//    val d: Array[Array[Long]] =
//      Array.tabulate(V)(s => dijkstra(s + 1, V, V - 1, G))
//
//    val r = (0 until Q).map { i =>
//      d(X(i)-1)(K) + d(K-1)(Y(i))
//    }. mkString("\n")
//
//    println(r)

    // ↑普通にTLE食らう
    // ここで, ダイクストラはO(V^3)で, 普通に V <= 10^5 は通らない..
    // O(V)かO(VlogV)ぐらいにしたい
    // 常に頂点Kを経由する. しかも無向グラフなので,
    // xjからKへの最短路 <=> Kからxjへの最短路であり
    // Kからの単一始点最短経路を求めるだけでいい...

    val d = dijkstra(K, V, V - 1, G)
    val r = (0 until Q).map { i =>
      d(X(i)) + d(Y(i))
    }. mkString("\n")

    println(r)
  }

}
