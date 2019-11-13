package ant.dijkstra.poj3255

import scala.collection.mutable.ArrayBuffer

object Main {

  def printArray(arr: Array[Int]): Unit =
    println(arr.mkString("\n"))

  // ある頂点vへの二番目の最短路は、他の頂点uへの最短路にu -> vを繋げたものか
  // uへの二番目の最短路にu -> vを繋げたものかのどちらかであるため,
  // 必要なのは全頂点への最短路ならびに二番目の最短路のみということになります
  // 最初のノードの二番目の最短路は, 存在しない
  // キューの先頭
  // vを取り出した辺の元、dを取り出した辺の推定最短距離（取り出した時点で最短距離が確定）
  // その二番目の最短距離よりも、最短距離が大きい時には次の処理に飛ばす
  // 辺の元から繋がる全ての辺について、見て行く
  // 最短距離から、取り出した辺のコストを追加したものを, d2とする
  // d2が, 接続先への最短距離よりも短いなら
  // 最短距離と, そうでない距離を入れ替える,
  // この時, 距離が更新されたので, キューにプッシュする
  // さらに, 現時点の二番目の最短距離よりも小さく、最短距離よりも大きい時
  // 二番目の最短距離を更新する
  // この時, 二番目の最短距離もキューにプッシュする
  // ダイクストラ法によって、同時に一番目と二番目の最短距離を更新していく

  case class Edge(to: Int, cost: Int)
  case class P(v: Int, d: Int)
  val INF = Int.MaxValue

  def solve(V: Int, E: Int, G: Array[ArrayBuffer[Edge]], start: Int, end: Int): Int = {
    val distance = Array.fill(V)(INF)
    val distance2 = Array.fill(V)(INF)

    distance(start) = 0
    val queue = scala.collection.mutable.PriorityQueue[P](P(start, 0))(
      Ordering.by[P, Int](p => p.d)(Ordering.Int.reverse))

    def relax(u: Int, v: Int, w: Int): Unit =
    // 緩和したときに, 推定最短距離を更新する場合
    // それを確定した点として格納する
    // distance(u) == INFなら, どうやっても最短距離は更新されない
      if (distance(u) != INF) {
        var dist = distance(u) + w
        if (distance(v) > dist) {
          dist = distance(v)
          distance(v) = distance(u) + w
          queue.enqueue(P(v, distance(v)))
        }
        if (distance2(v) > dist && distance(v) < dist) {
          distance2(v) = dist
          queue.enqueue(P(v, dist))
        }
      }

    while (queue.nonEmpty) {
      val P(u, _) = queue.dequeue()
      G(u) foreach { edge =>
        relax(u, edge.to, edge.cost)
      }
    }
    distance2(end)
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, R = sc.nextInt()
    val G = Array.fill(N)(new scala.collection.mutable.ArrayBuffer[Edge]())

    (0 until R) foreach { _ =>
      val u, v, cost = sc.nextInt()
      G(u-1).append(Edge(v-1, cost))
      G(v-1).append(Edge(u-1, cost))
    }

    println(solve(N, R, G, 0, N-1))
  }

}

