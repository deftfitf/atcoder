package jp.atcoder.arc033.c

object Main {

  trait Monoid[A] {
    def zero: A
    def op(a1: A, a2: A): A
  }

  trait Group[A] extends Monoid[A] {
    def inverse(a: A): A
  }

  class BIT[T: Group] private (n: Int) {

    implicit val g = implicitly[Group[T]]
    private[this] var tree: Vector[T] = Vector.fill(n+1)(g.zero)

    def sum(idx: Int): T = {
      @scala.annotation.tailrec
      def loop(i: Int, acm: T): T =
        if (i > 0) loop(i & (i - 1), g.op(acm, tree(i)))
        else acm
      loop(idx, g.zero)
    }

    def sum(start: Int, end: Int): T =
      if (start > 1) g.op(sum(end), g.inverse(sum(start - 1)))
      else sum(end)

    def add(idx: Int, value: T): Unit = {
      @scala.annotation.tailrec
      def loop(i: Int, tree: Vector[T]): Vector[T] =
        if (i <= n)
          loop(
            i + (i & -i),
            tree.updated(i, g.op(tree(i), value)))
        else tree
      tree = loop(idx, tree)
    }

  }

  object BIT {

    def apply[T: Group](size: Int): BIT[T] = new BIT(size)

  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val Q = sc.nextInt()
    val bldr = new StringBuilder
    val X_MAX = 200000
    implicit val intSumGroup: Group[Int] = new Group[Int] {
      override def inverse(a: Int): Int = -a
      override def zero: Int = 0
      override def op(a1: Int, a2: Int): Int = a1 + a2
    }
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
