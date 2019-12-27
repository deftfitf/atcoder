package jp.microad.atcoder.abc007.c

import scala.collection.immutable.Queue

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val R, C, sy, sx, gy, gx = sc.nextInt(); sc.nextLine()
    val c = Array.fill(R)(sc.nextLine().toCharArray)

    def solve(R: Int, C: Int, sx: Int, sy: Int, gx: Int, gy: Int): Int = {
      val distance = Array.fill(R, C)(-1)
      val dx = Array(-1, 0, 1, 0)
      val dy = Array(0, -1, 0, 1)
      @scala.annotation.tailrec
      def bfs(queue: Queue[(Int, Int)]): Int =
        if (queue.nonEmpty) {
          val ((x, y), next) = queue.dequeue
          if (x == gx && y == gy) distance(y)(x)
          else {
            bfs((0 until 4).foldLeft(next)((queue, i) => {
              val nx = x + dx(i)
              val ny = y + dy(i)
              if (nx >= 0 && nx < C && ny >= 0 && ny < R &&
                  c(ny)(nx) == '.' && distance(ny)(nx) < 0) {
                distance(ny)(nx) = distance(y)(x) + 1
                queue.enqueue((nx, ny))
              } else queue
            }))
          }
        } else -1
      distance(sy)(sx) = 0
      bfs(Queue((sx, sy)))
    }

    println(solve(R, C, sx - 1, sy - 1, gx - 1, gy - 1))
  }

}
