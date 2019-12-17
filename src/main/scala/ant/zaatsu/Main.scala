package ant.zaatsu

import scala.annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val w = 10
    val h = 10
    val n = 5
    val x1 = Array(1, 1, 4, 9, 10)
    val x2 = Array(6, 10, 4, 9, 10)
    val y1 = Array(4, 8, 1, 1, 6)
    val y2 = Array(4, 8, 10, 5, 10)

    def compress(x1: Array[Int], x2: Array[Int]): Int = {
      var lst: List[Int] = Nil
      for {
        i <- 0 until n
        d <- -1 to 1
        xt1 = x1(i) + d
        xt2 = x2(i) + d
      } {
        lst = x1(i) :: x2(i) :: lst
        if (xt1 >= 1 && xt1 <= w) lst ::= xt1
        if (xt2 >= 1 && xt2 <= w) lst ::= xt2
      }
      val f = lst.toSet.toIndexedSeq.sorted
      for {
        i <- 0 until n
      } {
        x1(i) = indexOf(f, x1(i))
        x2(i) = indexOf(f, x2(i))
      }
      f.size
    }

    def indexOf(indexedSeq: IndexedSeq[Int], value: Int): Int = {
      @tailrec
      def loop(left: Int, right: Int): Int =
        if (right - left > 1) {
          val mid = left + (right - left) / 2
          if (indexedSeq(mid) >= value) loop(left, mid)
          else loop(mid, right)
        } else right
      loop(-1, indexedSeq.size)
    }

    val W = compress(x1, x2)
    val H = compress(y1, y2)
    val A = Array.fill(H, W)(0)

    for {
      i <- 0 until n
      y <- y1(i) to y2(i)
      x <- x1(i) to x2(i)
    } A(y)(x) = 1

    def dfs(W: Int, H: Int, A: Array[Array[Int]]): Int = {
      val visited = Array.fill(H, W)(false)
      val dx = Array(-1, 0, 1, 0)
      val dy = Array(0, -1, 0, 1)

      def loop(stack: List[(Int, Int)]): Unit =
        if (stack.nonEmpty) {
          val (x, y) = stack.head
          if (!visited(y)(x)) {
            visited(y)(x) = true
            loop((0 until 4).foldLeft(stack.tail)(
              (b, i) => {
                val nx = x + dx(i)
                val ny = y + dy(i)
                if (nx >= 0 && nx < W && ny >= 0 && ny < H && A(y)(x) == 0) (nx, ny) :: b else b
              }))
          } else loop(stack.tail)
        }

      var count = 0
      for {
        y <- 0 until H
        x <- 0 until W
      } if (!visited(y)(x) && A(y)(x) == 0) {
        count += 1
        loop((x, y) :: Nil)
      }
      count
    }

    println(dfs(W, H, A))
  }

}
