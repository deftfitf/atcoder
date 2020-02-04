package jp.atcoder.aoj.segmenttree

object Main {

  trait Monoid[A] {
    def zero: A
    def op(a1: A, a2: A): A
  }

  class SegmentTree[A: Monoid] private (n: Int) {

    private[this] val m = implicitly[Monoid[A]]
    private[this] var tree = Vector.fill(2 * n - 1)(m.zero)

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
      new SegmentTree[A](n)
    }

  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, Q = sc.nextInt()

    implicit val m: Monoid[Int] = new Monoid[Int] {
      override def zero: Int = Int.MaxValue
      override def op(a1: Int, a2: Int): Int = a1 min a2
    }
    val segTree = SegmentTree[Int](N)

    val r = (0 until Q).foldLeft(new StringBuilder)((bldr, _) => {
      val c, x, y = sc.nextInt()
      c match {
        case 0 => segTree.update(x, y); bldr
        case 1 => bldr.append(segTree.query(x, y+1)).append("\n")
      }
    }).result()

    print(r)
  }

}

