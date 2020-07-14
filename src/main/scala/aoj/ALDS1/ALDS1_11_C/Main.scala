package aoj.ALDS1.ALDS1_11_C

import scala.collection.immutable.Queue
import scala.collection.mutable.ArrayBuffer

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V = sc.nextInt()
    val G = Array.fill(V+1)(new ArrayBuffer[Int]())
    for (_ <- 1 to V) {
      val v, k = sc.nextInt()
      for (_ <- 0 until k) {
        G(v).append(sc.nextInt())
      }
    }

    val dist = Array.fill(V)(-1)
    def bfs(queue: Queue[Int]): Unit =
      if (queue.nonEmpty) {
        val u = queue.head
        val candidates = G(u).filter(v => dist(v-1) == -1)
        candidates.foreach(v => dist(v-1) = dist(u-1) + 1)
        bfs(queue.tail.enqueue(candidates.toList))
      }
    dist(0) = 0
    bfs(Queue(1))
    val r = dist.zipWithIndex.map { case (d, v) => s"${v+1} $d" }.mkString("\n")
    println(r)
  }

}
