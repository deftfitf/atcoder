package jp.atcoder.arc033.c

object Main {

  class BIT private (n: Int) {

    private[this] var tree: Vector[Int] = Vector.fill(n+1)(0)

    def sum(idx: Int): Int = {
      @scala.annotation.tailrec
      def loop(i: Int, acm: Int): Int =
        if (i > 0) loop(i & (i - 1), acm + tree(i))
        else acm
      loop(idx, 0)
    }

    def sum(start: Int, end: Int): Int =
      if (start > 1) sum(end) - sum(start - 1)
      else sum(end)

    def add(idx: Int, value: Int): Unit = {
      @scala.annotation.tailrec
      def loop(i: Int, tree: Vector[Int]): Vector[Int] =
        if (i < n)
          loop(
            i + (i & -i),
            tree.updated(i, tree(i) + value))
        else tree
      tree = loop(idx, tree)
    }

  }

  object BIT {

    def apply(size: Int): BIT = {
      var n = 1
      while (n < size) n <<= 1
      new BIT(n)
    }

  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val Q = sc.nextInt()
    val bldr = new StringBuilder
    val X_MAX = 200000
    val bit = BIT(X_MAX)
    for {
      _ <- 0 until Q
    } {
      val T, X = sc.nextInt()
      T match {
        case 1 => bit.add(X, 1)
        case 2 =>
          val n = binarySearch(X)
          bldr.append(n).append("\n")
          bit.add(n, -1)
      }
    }

    def binarySearch(X: Int): Int = {
      def loop(l: Int, r: Int): Int =
        if (r - l > 1) {
          val mid = l + (r - l) / 2
          if (bit.sum(mid) >= X) loop(l, mid)
          else loop(mid, r)
        } else r
      loop(-1, X_MAX)
    }

    print(bldr.result())
  }

}
