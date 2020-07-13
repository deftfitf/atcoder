package jp.atcoder.abc138.d

import scala.collection.immutable.Queue
import scala.collection.mutable.ArrayBuffer

object Main2 {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val N, Q = sc.nextInt()

    // 操作jのたびに, その頂点自体にカウントを足しておいて, 最後に頂点から辿る時に, カウントを全て合算すればいい
    val G = Array.fill(N+1)(new ArrayBuffer[Int]())
    for (_ <- 0 until (N - 1)) {
      val a, b = sc.nextInt()
      G(a).append(b)
      G(b).append(a)
    }
    val P = Array.fill(Q, 2)(sc.nextInt())
    val C = new Array[Int](N+1)
    P.foreach { case Array(p, x) => C(p) += x }

    def solve(V: Int, G: Array[ArrayBuffer[Int]], C: Array[Int]): Array[Int] = {
      val visit = new Array[Boolean](V+1)
      def bfs(queue: Queue[Int]): Unit =
        if (queue.nonEmpty) {
          val u = queue.head
          val vertexes = G(u).filter(v => !visit(v))
          vertexes.foreach(v => {
            visit(u) = true
            C(v) += C(u)
          })
          bfs(vertexes.foldLeft(queue.tail)((queue, v) => queue.enqueue(v)))
        }
      bfs(Queue(1))
      C
    }

    val r = solve(N, G, C).tail.mkString(" ")
    println(r)
  }

}
