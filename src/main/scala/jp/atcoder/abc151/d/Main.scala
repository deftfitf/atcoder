package jp.atcoder.abc151.d

import scala.collection.immutable.Queue

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W = sc.nextInt(); sc.nextLine()
    val S = Array.fill(H)(sc.nextLine().trim.toCharArray)
    // スタートの決定にO(H*W)
    // ゴールの決定にO(H*W)
    // 最短路の探索にO(H*W)
    // 全探索するとO(H^3*W^3)
    // 64 * 10^6で間に合わない
    // dist(y)(x)(y')(x') := マスy,xからマスy',x'への最短路としておく
//    def solve(H: Int, W: Int, S: Array[Array[Char]]): Int = {
//      val MAX = Int.MaxValue
//      val dist = Array.fill(H, W, H, W)(MAX)
//      val dx = Array(0, 0, -1, 1)
//      val dy = Array(-1, 1, 0, 0)
//
//      def bfs(sx: Int, sy: Int, ex: Int, ey: Int): Int = {
//        val UNREACHED = 401
//        val arr = Array.fill(H, W)(MAX)
//        arr(sy)(sx) = 0
//        def loop(queue: Queue[(Int, Int)], min: Int): Int = {
//          if (queue.nonEmpty) {
//            val (x, y) = queue.head
//            if (x == ex && y == ey) {
//              val d = min min arr(ey)(ex)
//              dist(sy)(sx)(y)(x) = d
//              dist(y)(x)(sy)(sx) = d
//              d
//            } else if (dist(y)(x)(ey)(ex) != MAX) {
//              loop(queue.tail, min min (arr(y)(x) + dist(y)(x)(ey)(ex)))
//            } else {
//              if (min == UNREACHED) {
//                dist(sy)(sx)(y)(x) = arr(y)(x)
//                dist(y)(x)(sy)(sx) = arr(y)(x)
//              }
//              loop((0 to 3).foldLeft(queue.tail) { (queue, i) =>
//                val nx = x + dx(i)
//                val ny = y + dy(i)
//                if (nx >= 0 && nx < W &&
//                  ny >= 0 && ny < H &&
//                  S(ny)(nx) == '.' &&
//                  arr(ny)(nx) == MAX) {
//                  arr(ny)(nx) = arr(y)(x) + 1
//                  queue.enqueue((nx, ny))
//                } else queue
//              }, min)
//            }
//          } else min
//        }
//        loop(Queue((sx, sy)), UNREACHED)
//      }
//
//      (for {
//        sy <- 0 until H
//        sx <- 0 until W
//        if (S(sy)(sx) == '.')
//        ey <- (0 until H).reverse
//        ex <- (0 until W).reverse
//        if (S(ey)(ex) == '.')
//        if ey > sy || (ey == sy && ex > sx)
//      } yield bfs(sx, sy, ex, ey)).max
//    }

    // 開始地点と終了地点を全探索していたが, そもそも開始地点を確定させて
    // BFSをすると, 一回探索した時に全ての終了地点までの最短経路が求まるので
    // 開始地点の列挙O(HW)に、BFSで最後まで探索した時の距離 O(HW）を求め、その最大を求めればいい
    def solve(H: Int, W: Int, S: Array[Array[Char]]): Int = {
      val dx = Array(0, 0, -1, 1)
      val dy = Array(1, -1, 0, 0)
      def bfs(sx: Int, sy: Int): Int = {
        val visited = Array.fill(H, W)(false)
        def loop(queue: Queue[((Int, Int), Int)], max: Int): Int =
          if (queue.nonEmpty) {
            val ((x, y), dist) = queue.head
            loop((0 until 4).foldLeft(queue.tail)((queue, i) => {
              val nx = x + dx(i)
              val ny = y + dy(i)
              if (nx >= 0 && nx < W &&
                  ny >= 0 && ny < H &&
                  S(ny)(nx) == '.' &&
                  !visited(ny)(nx)) {
                visited(ny)(nx) = true
                queue.enqueue(((nx, ny), dist + 1))
              } else queue
            }), max max dist)
          } else max
        visited(sy)(sx) = true
        loop(Queue(((sx, sy), 0)), 0)
      }

      (for {
        sy <- 0 until H
        sx <- 0 until W
        if S(sy)(sx) == '.'
      } yield bfs(sx, sy)).max
    }

    println(solve(H, W, S))
  }

}
