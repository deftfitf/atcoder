package jp.atcoder.abc137.e

object Main {

  val INF = Int.MinValue

  case class Edge(u: Int, v: Int, weight: Int)
  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E, P = sc.nextInt()

    // つまり, 辺を通る時, Pは損失されているので
    // 重みからPを引いておく.
    // -{w(u,v) - P} を重みとした単一始点最短路問題を解く
    // また, 正閉路が存在する場合, w(u,v) - P > 0の場合, 無限にコインを増やすことができる
    // そのため, 閉路判定を行う必要がある
    val edges = (0 until E).foldLeft(Nil: List[Edge])((lst, _) => {
      val u, v, c = sc.nextInt()
      Edge(u, v, c - P) :: lst
    })

    bellmanFordMax(V, edges, 1, V) match {
      case Some(score) => println(score)
      case None => println(-1)
    }
  }

  @annotation.Graph.BellmanFord
  def bellmanFordMax(V: Int, edges: List[Edge], s: Int, t: Int): Option[Int] = {
    val d = Array.fill(V+1)(INF)

    d(s) = 0
    for {
      _ <- 0 until V - 1
      edge <- edges
    } if (d(edge.u) != INF && d(edge.v) < d(edge.u) + edge.weight) {
      d(edge.v) = d(edge.u) + edge.weight
    }
    val max = d(t)

    val negative = new Array[Boolean](V+1)
    for {
      _ <- 0 until V
      edge <- edges
    } if (d(edge.u) != INF && d(edge.v) < d(edge.u) + edge.weight) {
      d(edge.v) = d(edge.u) + edge.weight
      negative(edge.v) = true
    } else if (negative(edge.u)) {
      negative(edge.v) = true
    }

    if (!negative(V)) Some(max max 0)
    else None
  }

}
