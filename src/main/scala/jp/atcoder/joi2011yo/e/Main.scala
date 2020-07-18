package jp.atcoder.joi2011yo.e

import scala.collection.immutable.Queue

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W, N = sc.nextInt(); sc.nextLine()
    val F: Array[Array[Char]] = Array.fill(H)(sc.nextLine().toCharArray)

    // H <= 1000, W <= 1000, N <= 9
    // 巣S, 障害物X, 空き地., 硬さxのチーズ工場x
    // ネズミは全てのチーズを食べられることが保証されている
    // ネズミが全てのチーズを食べ終えるまでにかかる最短時間を表す整数を示せ
    // 制約から, ネズミは, 柔らかいチーズから順に食べることしかできない
    // そのため, S => 1の最短路 + 1 => 2の最短路 + ... + 8 => 9の最短路を合計したものになる

    val vector = List((0, 1), (1, 0), (0, -1), (-1, 0))
    def bfs(sx: Int, sy: Int, gx: Int, gy: Int): Int = {
      val distance = Array.fill(H, W)(-1)
      distance(sy)(sx) = 0
      @scala.annotation.tailrec
      def recursive(queue: Queue[(Int, Int)]): Unit =
        if (queue.nonEmpty) {
          val (x, y) = queue.head
          if (!(x == gx && y == gy)) {
            val candidates =
              vector
                .map { case (dx, dy) => (x + dx, y + dy) }
                .filter { case (x, y) => x >= 0 && x < W && y >= 0 && y < H && distance(y)(x) == -1 && F(y)(x) != 'X' }
            candidates.foreach { case (xx, yy) => distance(yy)(xx) = distance(y)(x) + 1 }
            recursive(queue.tail.enqueue(candidates))
          }
        }
      recursive(Queue((sx, sy)))
      distance(gy)(gx)
    }

    val P = new Array[(Int, Int)](10)
    for {
      y <- 0 until H
      x <- 0 until W
    } {
      if (F(y)(x) == 'S') P(0) = (x, y)
      else if (!(F(y)(x) == '.' || F(y)(x) == 'X')) P(F(y)(x) - '0') = (x, y)
    }
    val r = P.filter(_ != null).sliding(2).foldLeft(0) { case (sum, Array((sx, sy), (gx, gy))) => sum + bfs(sx, sy, gx, gy) }
    println(r)
  }

}
