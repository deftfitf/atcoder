package lib.graph

import scala.collection.mutable

object Dijkstra_AdjacencyListHeap {

  case class Edge(v: Int, w: Long)

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val INF = Long.MaxValue
    val V, E, r = sc.nextInt()
    val G = Array.fill(V)(List[Edge]())
    (0 until E) foreach { _ =>
      val u, v, w = sc.nextInt()
      G(u) ::= Edge(v, w)
    }

    def dijkstra(s: Int, V: Int, E: Int, G: Array[List[Edge]]): Array[Long] = {
      val d = Array.fill[Long](V)(INF)
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

    println(dijkstra(r, V, E, G).map(d => if (d == INF) "INF" else d).mkString("\n"))
  }

}
