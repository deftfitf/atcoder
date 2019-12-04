package lib.graph

object Dijkstra_AdjacencyList {

  case class Edge(v: Int, w: Int)

  def main(args: Array[String]): Unit = {
    val INF = Integer.MAX_VALUE
    val sc = new java.util.Scanner(System.in)

    val V, E, r = sc.nextInt()
    val G = Array.fill(V)(List[Edge]())
    (0 until E) foreach { _ =>
      val u, v, w = sc.nextInt()
      G(u) ::= Edge(v, w)
    }

    def dijkstra(s: Int, V: Int, E: Int, G: Array[List[Edge]]): Array[Int] = {
      val used = new Array[Boolean](V)
      val d = Array.fill(V)(INF)
      d(s) = 0
      def loop(): Unit = {
        val notUsed = (0 until V).filter(u => !used(u))
        if (notUsed.nonEmpty) {
          val u = notUsed.minBy(d)
          used(u) = true
          G(u) foreach { edge =>
            if (d(u) != INF) {
              d(edge.v) = d(edge.v) min (d(u) + edge.w)
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
