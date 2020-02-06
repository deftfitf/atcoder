package ant.bit

object POJ2104 {

  trait Monoid[A] {
    def zero: A
    def op(a1: A, a2: A): A
  }

  class SegmentTree[A: Monoid] private (
      n: Int, private[this] var tree: Vector[A]) {

    private[this] val m = implicitly[Monoid[A]]

    def update(idx: Int, value: A): Unit = {
      @scala.annotation.tailrec
      def loop(current: Int, tree: Vector[A]): Vector[A] =
        if (current > 0) {
          val next  = (current - 1) / 2
          val left  = tree(2 * next + 1)
          val right = tree(2 * next + 2)
          loop(next, tree.updated(next, m.op(left, right)))
        } else tree
      val current = n - 1 + idx
      tree = loop(current, tree.updated(current, value))
    }

    def query(start: Int, end: Int): A = {
      def loop(current: Int, left: Int, right: Int): A =
        if (start <= left && end >= right) tree(current)
        else if (start >= right || end <= left) m.zero
        else m.op(
          loop(2 * current + 1, left, (left + right) / 2),
          loop(2 * current + 2, (left + right) / 2, right))
      loop(0, 0, n)
    }

  }

  object SegmentTree {

    def apply[A: Monoid](size: Int): SegmentTree[A] = {
      var n = 1
      while (n < size) n <<= 1
      new SegmentTree[A](n, Vector.fill(2 * n - 1)(implicitly[Monoid[A]].zero))
    }

  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    implicit val sortedListMonoid: Monoid[Vector[Int]] = new Monoid[Vector[Int]] {
      override def zero: Vector[Int] = Vector.empty
      override def op(a1: Vector[Int], a2: Vector[Int]): Vector[Int] = {
        def loop(a1: Vector[Int], a2: Vector[Int], sorted: Vector[Int]): Vector[Int] =
          if (a1.isEmpty) {
            if (a2.isEmpty) sorted
            else sorted ++ a2
          } else if (a2.isEmpty) sorted ++ a1
            else if (a1.head < a2.head) loop(a1.tail, a2, sorted :+ a1.head)
            else loop(a1, a2.tail, sorted :+ a2.head)
        loop(a1, a2, Vector.empty)
      }
    }
    val bit = SegmentTree(N)
    for {
      i <- 0 until N
    } bit.update(i, Vector(A(i)))

    for {
      _ <- 0 until M
    } {
      val i, j, k = sc.nextInt()
      println(bit.query(i-1, j)(k-1))
    }
  }

}
