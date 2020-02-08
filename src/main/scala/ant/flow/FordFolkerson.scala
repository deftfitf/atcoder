package ant.flow

import scala.collection.mutable.ArrayBuffer

object FordFolkerson {

  case class Edge(to: Int, capacity: Int, rev: Int) {
    def pour(flow: Int): Edge = copy(capacity = capacity - flow)
    def reverse(flow: Int): Edge = copy(capacity = capacity + flow)
  }

  def maxFlow(V: Int, G: Array[ArrayBuffer[Edge]], source: Int, sink: Int): Int = {
    @scala.annotation.tailrec
    def loop(flow: Int): Int = {
      val f = dfs()
      if (f > 0) loop(flow + f)
      else flow
    }

    def dfs(): Int = {
      val visited = new Array[Boolean](V)
      @scala.annotation.tailrec
      def loop(stack: List[(Int, List[(Int, Int)])]): Int =
        if (stack.nonEmpty) {
          val (u, path) = stack.head
          if (u == sink) {
            val flow = capacity(path)
            pour(path, flow)
            flow
          } else if (!visited(u)) {
            visited(u) = true
            loop(
              G(u).indices.foldLeft(stack.tail)((stack, j) =>
                if (G(u)(j).capacity > 0) (G(u)(j).to, (u, j) :: path) :: stack
                else stack))
          } else loop(stack.tail)
        } else 0

      loop((source, Nil) :: Nil)
    }

    def capacity(path: List[(Int, Int)]): Int = {
      @scala.annotation.tailrec
      def loop(path: List[(Int, Int)], min: Int): Int =
        path match {
          case Nil => min
          case (u, i) :: t => loop(t, min min G(u)(i).capacity)
        }
      loop(path, Int.MaxValue)
    }

    def pour(path: List[(Int, Int)], capacity: Int): Unit = {
      @scala.annotation.tailrec
      def loop(path: List[(Int, Int)]): Unit =
        path match {
          case (u, i) :: t =>
            val edge = G(u)(i)
            G(u)(i) = edge.pour(capacity)
            G(edge.to)(edge.rev) = G(edge.to)(edge.rev).reverse(capacity)
            loop(t)
          case _ => ()
        }
      loop(path)
    }

    loop(0)
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E = sc.nextInt()
    val G = Array.fill(V+1)(new ArrayBuffer[Edge]())
    for {
      _ <- 0 until E
    } {
      val u, v, c = sc.nextInt()
      G(u+1).append(Edge(v+1, c, G(v+1).size))
      G(v+1).append(Edge(u+1, 0, G(u+1).size - 1))
    }

    println(maxFlow(V, G, 1, V))
  }

}
