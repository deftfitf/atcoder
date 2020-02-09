package ant.matching

import scala.collection.mutable.ArrayBuffer

object GRL7A {

  case class Edge(to: Int, capacity: Short, rev: Int) {
    def removed: Edge = copy(capacity = 0)
    def added: Edge = copy(capacity = 1)
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val X, Y, E = sc.nextInt()
    val G = Array.fill(X + Y + 2)(new ArrayBuffer[Edge]())
    val ss = X + Y
    val st = X + Y + 1

    def addEdge(from: Int, to: Int): Unit = {
      G(from).append(Edge(to, 1, G(to).size))
      G(to).append(Edge(from, 0, G(from).size - 1))
    }

    // 超入口の導入
    for {
      x <- 0 until X
    } addEdge(ss, x)

    // 超出口の導入
    for {
      y <- 0 until Y
    } addEdge(X + y, st)

    // フローネットワークの構築
    for {
      _ <- 0 until E
    } {
      val x, y = sc.nextInt()
      addEdge(x, X + y)
    }

    println(maximumFlow(X + Y + 2, G, ss, st))
  }

  def maximumFlow(V: Int, G: Array[ArrayBuffer[Edge]], source: Int, sink: Int): Int = {
    def dfs(): Short = {
      val visit = new Array[Boolean](V)
      @scala.annotation.tailrec
      def loop(stack: List[(Int, List[(Int, Int)])]): Short =
        if (stack.nonEmpty) {
          val (u, path) = stack.head
          if (u == sink) {
            pour(G, path)
            1
          } else if (!visit(u)) {
            visit(u) = true
            loop(
              G(u).indices.foldLeft(stack.tail)((stack, j) =>
                if (G(u)(j).capacity > 0) (G(u)(j).to, (u, j) :: path) :: stack
                else stack))
          } else loop(stack.tail)
        } else 0
      loop((source, Nil) :: Nil)
    }

    @scala.annotation.tailrec
    def loop(flow: Int): Int = {
      val f = dfs()
      if (f > 0) loop(flow + f)
      else flow
    }

    loop(0)
  }

  def pour(G: Array[ArrayBuffer[Edge]], path: List[(Int, Int)]): Unit =
    for {
      e <- path
    } {
      val (u, i) = e
      val edge = G(u)(i)
      G(u)(i) = G(u)(i).removed
      G(edge.to)(edge.rev) = G(edge.to)(edge.rev).added
    }

}
