package jp.atcoder.aoj.grl.grl1a

import scala.collection.mutable.ArrayBuffer

@annotation.Graph.BellmanFord
object BellmanFord {

  val INF = Int.MaxValue

  // BellmanFord: O(VE) (CAN'T AC)
//  case class Edge(from: Int, to: Int, weight: Int)
//  def bellmanFord(V: Int, edges: List[Edge], s: Int): (Array[Int], Array[Int]) = {
//    val sspTree = Array.fill(V)(-1)
//    val dist = Array.fill(V)(INF)
//
//    dist(s) = 0
//    def relax(edge: Edge): Unit =
//      if (dist(edge.from) != INF &&
//          dist(edge.to) > dist(edge.from) + edge.weight) {
//        dist(edge.to) = dist(edge.from) + edge.weight
//        sspTree(edge.to) = edge.from
//      }
//
//    for {
//      _ <- 0 until V - 1
//      edge <- edges
//    } relax(edge)
//    (dist, sspTree)
//  }
//  def main(args: Array[String]): Unit = {
//    val sc = new java.util.Scanner(System.in)
//
//    val V, E, r = sc.nextInt()
//
//    val edges = (0 until E).foldLeft(Nil: List[Edge])((lst, _) => {
//      val s, t, d = sc.nextInt()
//      Edge(s, t, d) :: lst
//    })
//
//    val (d, _) = bellmanFord(V, edges, r)
//    val result = (0 until V).foldLeft(new StringBuilder)((bldr, u) =>
//      if (d(u) == INF) bldr.append("INF\n")
//      else bldr.append(d(u)).append("\n")).result()
//    print(result)
//  }

  // BellmanFord: O(V+E) with topological sort, for only DAG. (CAN'T AC, NOT DAG)
  case class Edge(to: Int, weight: Int)
  def bellmanFord(V: Int, G: Array[ArrayBuffer[Edge]], s: Int): (Array[Int], Array[Int]) = {
    val sspTree = Array.fill(V)(-1)
    val dist = Array.fill(V)(INF)

    def relax(u: Int, v: Int, w: Int): Unit =
      if (dist(u) != INF && dist(v) > dist(u) + w) {
        dist(v) = dist(u) + w
        sspTree(v) = u
      }

    dist(s) = 0
    for {
      u <- topologicalSort(V, G, s)
      e <- G(u)
    } relax(u, e.to, e.weight)

    (dist, sspTree)
  }

  // Gは連結かつ,sからたどり着ける点のみ見ればいいのでsから一回だけdfsすればいい
  private def topologicalSort(V: Int, G: Array[ArrayBuffer[Edge]], s: Int): List[Int] = {
    val visited = new Array[Boolean](V)
    def dfs(stack: List[Int], sorted: List[Int]): List[Int] =
      if (stack.nonEmpty) {
        val u = stack.head
        if (u < 0) dfs(stack.tail, (-u)-1 :: sorted)
        else if (!visited(u)) {
          visited(u) = true
          dfs(
            G(u).foldLeft(-(u+1) :: stack.tail)(
              (stack, e) => e.to :: stack), sorted)
        } else dfs(stack.tail, sorted)
      } else sorted
    dfs(s :: Nil, Nil)
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E, r = sc.nextInt()
    val G = Array.fill(V)(new ArrayBuffer[Edge]())
    for {
      _ <- 0 until E
    } {
      val s, t, d = sc.nextInt()
      G(s).append(Edge(t, d))
    }

    val (d, _) = bellmanFord(V, G, r)
    val result = (0 until V).foldLeft(new StringBuilder)((bldr, u) =>
      if (d(u) == INF) bldr.append("INF\n")
      else bldr.append(d(u)).append("\n")).result()
    print(result)
  }

}
