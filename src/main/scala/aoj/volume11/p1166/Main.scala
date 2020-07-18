package aoj.volume11.p1166

import scala.collection.immutable.Queue

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    // 資格を縦横に並べた長方形の領域, 出入り口をのぞいて壁で囲まれている
    // 迷路の入り口は長方形の上辺の最も左側, 出口は同様に下編の最も右側である
    // 入り口から、出口までの最短経路の長さを求めること
    // 最短経路が複数あることもあれば, 一つもないこともある
    // 通常の場合とは異なり, 区画自体が障害物とかではなく, 壁があるかないかを調べる
    // 2 x h - 1行は, 隣接する資格の間の壁の有無を示している
    // 最初の行の先頭は空白で, その後にはw - 1個の1または0が空白1つで区切られて並んでいる
    // これらは一番上の行の横に隣り合う四角の壁有無を示す
    // 次の行の先頭には空白はなく, w個の1または0が空白1つを区切りに続く
    // これらは最初と次の行に隣り合う壁の有無を表す

    val vector = List((0, 1), (1, 0), (0, -1), (-1, 0))
    def bfs(W: Int, H: Int,
            verticalWalls: Array[Array[Int]],
            horizontalWalls: Array[Array[Int]]): Int = {
      val dist = Array.fill(H, W)(0)
      def recursive(queue: Queue[(Int, Int)]): Unit =
        if (queue.nonEmpty) {
          val (x, y) = queue.head
          val candidates = vector
            .filter { dv =>
              val nx = x + dv._1
              val ny = y + dv._2
              nx >= 0 && nx < W && ny >= 0 && ny < H &&
                dist(ny)(nx) == 0 &&
                (dv match {
                  case (0, 1) => y == H - 1 || horizontalWalls(y)(x) == 0
                  case (1, 0) => x == W - 1 || verticalWalls(y)(x) == 0
                  case (0, -1) => y == 0 || horizontalWalls(y-1)(x) == 0
                  case (-1, 0) => x == 0 || verticalWalls(y)(x-1) == 0
                })
            }
            .map { case (dx, dy) => (x + dx, y + dy) }
          candidates foreach { case (xx, yy) => dist(yy)(xx) = dist(y)(x) + 1 }
          recursive(queue.tail.enqueue(candidates))
        }
      dist(0)(0) = 0
      recursive(Queue((0, 0)))
      if (dist(H - 1)(W - 1) != 0) dist(H - 1)(W - 1) + 1
      else 0
    }

    while (true) {
      val W, H = sc.nextInt()
      if (W == 0 && H == 0) {
        return
      }
      val verticalWalls = Array.fill(H, W - 1)(0)
      val horizontalWalls = Array.fill(H - 1, W)(0)
      for {
        y <- 0 until H - 1
      } {
        (0 until W - 1).foreach(x => verticalWalls(y)(x) = sc.nextInt())
        (0 until W).foreach(x => horizontalWalls(y)(x) = sc.nextInt())
      }
      (0 until W - 1).foreach(x => verticalWalls(H - 1)(x) = sc.nextInt())
      val r = bfs(W, H, verticalWalls, horizontalWalls)
      println(r)
    }

  }

}
