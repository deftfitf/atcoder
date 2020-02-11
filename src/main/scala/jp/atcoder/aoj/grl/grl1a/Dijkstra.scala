package jp.atcoder.aoj.grl.grl1a

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Dijkstra {

  val INF = Int.MaxValue

  case class Edge(to: Int, weight: Int)
  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E, r = sc.nextInt()
    val G = Array.fill(V)(new ArrayBuffer[Edge]())
    for {
      _ <- 0 until E
    } {
      val s, t, w = sc.nextInt()
      G(s).append(Edge(t, w))
    }

    val (d, _) = dijkstra(V, G, r)
    val result = (0 until V).foldLeft(new mutable.StringBuilder())(
      (bldr, v) =>
        if (d(v) == INF) bldr.append("INF\n")
        else bldr.append(d(v)).append("\n")).result()

    print(result)
  }

  @annotation.Graph.Prim
  def dijkstra(V: Int, G: Array[ArrayBuffer[Edge]], s: Int): (Array[Int], Array[Int]) = {
    val sspTree = Array.fill(V)(-1)
    val dist = Array.fill(V)(INF)
    val used = new Array[Boolean](V)
    val queue =
      new mutable.PriorityQueue[(Int, Int)]()(
        Ordering.by[(Int, Int), Int](_._2).reverse)

    dist(s) = 0
    queue.enqueue((s, 0))
    while (queue.nonEmpty) {
      val (u, _) = queue.dequeue()
      if (!used(u)) {
        used(u) = true
        G(u).foreach(e => relax(u, e.to, e.weight, queue))
      }
    }

    def relax(u: Int, v: Int, w: Int, queue: mutable.PriorityQueue[(Int, Int)]): Unit =
      if (dist(u) != INF && dist(v) > dist(u) + w) {
        dist(v) = dist(u) + w
        sspTree(v) = u
        queue.enqueue((v, dist(v)))
      }

    (dist, sspTree)
  }

}
