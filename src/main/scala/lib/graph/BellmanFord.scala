package lib.graph

object BellmanFord {

  case class Edge(v: Int, w: Int)

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val INF = Int.MaxValue
    val NIL = -1
    val V, E, r = sc.nextInt()
    val G = Array.fill(V)(List[Edge]())
    (0 until E) foreach { _ =>
      val u, v, cost = sc.nextInt()
      G(u) ::= Edge(v, cost)
    }


    val d = Array.fill(V)(INF)
    val parent = Array.fill(V)(NIL)
    def solve(): Boolean = {
      (0 until V - 1) foreach { _ =>
        (0 until V) foreach { u =>
          G(u) foreach { edge =>
            if (d(u) != INF && d(edge.v) > d(u) + edge.w) {
              d(edge.v) = d(u) + edge.w
              parent(edge.v) = u
            }
          }
        }
      }

      var isCyclic = false
      (0 until V - 1) foreach { u =>
        G(u) foreach { edge =>
          if (d(u) != INF && d(edge.v) > d(u) + edge.w) {
            isCyclic = true
          }
        }
      }
      isCyclic
    }

    d(r) = 0
    solve()
    val result = G.indices.map(d)
      .map(w => if (w == INF) "INF" else w).mkString("\n")

    println(result)
  }

}
