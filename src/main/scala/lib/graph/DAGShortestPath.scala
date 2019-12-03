package lib.graph

object DAGShortestPath {

  case class Edge(v: Int, w: Int)

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val V, E, source = sc.nextInt()
    val G = Array.fill(V+1)(List[Edge]())
    val Dim = Array.fill(V+1)(0)
    (0 until E) foreach { _ =>
      val u, v, w = sc.nextInt()
      G(u+1) ::= Edge(v+1, w)
      Dim(v+1) += 1
    }

    def topologicalSort(V: Int, E: Int, G: Array[List[Edge]], Dim: Array[Int]): List[Int] = {
      val visit = new Array[Boolean](V+1)

      def loop(stack: List[Int], sorted: List[Int]): List[Int] =
        if (stack.nonEmpty) {
          val u = stack.head
          if (u < 0) {
            loop(stack.tail, -u :: sorted)
          } else if (!visit(u)) {
            visit(u) = true
            loop(G(u).foldLeft(-u :: stack.tail)((stack, edge) =>
              if (!visit(edge.v)) {
                edge.v :: stack
              } else stack), sorted)
          } else loop(stack.tail, sorted)
        } else sorted

      def eachRoot(s: Int, sorted: List[Int]): List[Int] =
        if (s <= V) {
          if (Dim(s) == 0) eachRoot(s + 1, loop(s :: Nil, Nil) ::: sorted)
          else eachRoot(s + 1, sorted)
        } else sorted

      eachRoot(1, Nil)
    }

    val INF = Int.MaxValue
    val d = Array.fill(V)(INF)
    d(source) = 0
    topologicalSort(V, E, G, Dim).map(_ - 1) foreach { u =>
      G(u) foreach { edge =>
        if (d(edge.v) > d(u) + edge.v) {
          d(edge.v) = d(u) + edge.v
        }
      }
    }
  }

}
