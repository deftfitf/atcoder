package jp.atcoder.abc006.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    // x + y + z = N
    // 2x + 3y + 4z = M を満たすx, y, zのうち, 1つを回答すれば良さそう
    // 式変形すると z = N - x - y
    // 2x + 3y + 4(N - x - y) = M => -2x - y = M - 4N => 2x + y = 4N - M
    // しかし, x, yをNだけそれぞれ調べると, 普通に間に合わない
    // x = (4N - M - y) / 2 として, yを二分探索すれば間に合いそう.
    // xよりも右辺が大きい時, yが小さい. 逆であればyが大きい. これを組み合わせればO(nlogn)になる

    def binarySearch(x: Int, left: Int, right: Int): Int = {
      if (!(4 * N - M - 2 * x >= 0 && 4 * N - M - 2 * x <= N)) {
        return -1
      }
      def recursive(left: Int, right: Int): Int =
        if (right - left > 1) {
          val mid = left + (right - left) / 2
          if (mid > 4 * N - M - 2 * x) recursive(left, mid)
          else recursive(mid, right)
        } else left
      recursive(left, right)
    }

    val (x, y, z) = (for {
       x <- 0 to N
       y = binarySearch(x, -1, N+1)
       if x + y <= N
       z = N - x - y
    } yield (x, y, z)).find(_._2 >= 0).getOrElse((-1, -1, -1))

    println(s"$x $y $z")
  }

}
