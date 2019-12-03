package ant.graph.bipartite

import scala.collection.mutable.ArrayBuffer

object BipartiteGraph {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E = sc.nextInt()
    val G = Array.fill(V)(new ArrayBuffer[Int]())
    (0 until E) foreach { _ =>
      val u, v = sc.nextInt()
      G(u).append(v)
      G(v).append(u)
    }

    def solve(): Boolean = {
      val FALSE = -1
      val color = new Array[Int](V)

      def dfs(stack: List[Int]): Boolean =
        if (stack.nonEmpty) {
          val u = stack.head
          def loop(i: Int, stack: List[Int]): List[Int] =
            if (i < G(u).size) {
              val v = G(u)(i)
              if (color(v) == color(u)) {
                FALSE :: Nil
              } else if (color(v) == 0) {
                color(v) = color(u) * -1
                loop(i+1, v :: stack)
              } else loop(i+1, stack)
            } else stack
          if (u == FALSE) false
          else {
            dfs(loop(0, stack.tail))
          }
        } else true

      (0 until V) forall { u =>
        if (color(u) == 0) {
          color(u) = 1
          dfs(u :: Nil)
        } else true
      }
    }

    println(if (solve()) "Yes" else "No")
  }

}
