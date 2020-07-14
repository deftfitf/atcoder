package jp.atcoder.abc007.c

import scala.collection.immutable.Queue

object Main2 {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val R, C = sc.nextInt()
    val sy, sx, gy, gx = sc.nextInt(); sc.nextLine()
    val c = Array.fill(R)(sc.nextLine().toCharArray)

    val dist = Array.fill(R, C)(-1)
    val vector = List((0, 1), (1, 0), (0, -1), (-1, 0))
    def bfs(queue: Queue[(Int, Int, Int)]): Int =
      if (queue.nonEmpty) {
        val (x, y, d) = queue.head
        if (x == (gx - 1) && y == (gy - 1)) {
          d
        } else if (x >= 0 && x < C && y >= 0 && y < R && c(y)(x) == '.' && dist(y)(x) < 0) {
          dist(y)(x) = d
          bfs(queue.tail.enqueue(vector.map { case (dx, dy) => (x + dx, y + dy, d + 1) }))
        } else bfs(queue.tail)
      } else -1

    val r = bfs(Queue((sx-1, sy-1, 0)))
    println(r)
  }

}
