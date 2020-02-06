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

    implicit val sortedListMonoid: Monoid[List[Int]] = new Monoid[List[Int]] {
      override def zero: List[Int] = Nil
      override def op(a1: List[Int], a2: List[Int]): List[Int] = {
        def loop(a1: List[Int], a2: List[Int], sorted: List[Int]): List[Int] =
          (a1, a2) match {
            case (Nil, Nil) => sorted
            case (Nil, h :: t) => loop(Nil, t, h :: sorted)
            case (h :: t, Nil) => loop(t, Nil, h :: sorted)
            case (h1 :: t1, h2 :: _) if h1 < h2 => loop(t1, a2, h1 :: sorted)
            case (_, h2 :: t2) => loop(a1, t2, h2 :: sorted)
          }
        loop(a1, a2, Nil).reverse
      }
    }
    val bit = SegmentTree(N)
    for {
      i <- 0 until N
    } bit.update(i, A(i) :: Nil)

    for {
      _ <- 0 until M
    } {
      val i, j, k = sc.nextInt()
      println(bit.query(i-1, j).take(k).last) // 二分探索をしないと実際には間に合わない
    }
  }

}
