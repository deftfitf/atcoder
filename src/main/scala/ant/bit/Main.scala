package ant.bit

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

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // 順番に, その数の左にある, その数より大きい数の数だけ交換する必要がある
    // j = 0 ~ n-1まで順番に見ていき, その数より大きい数の数だけカウントを増やしていく
    // その数以下の数は, bit.sum(A[j])で求まるので, j - bit.sum(A[j])で
    // その数より大きい数の数が求まる
    implicit val intGroup: Group[Int] = new Group[Int] {
      override def inverse(a: Int): Int = -a
      override def zero: Int = 0
      override def op(a1: Int, a2: Int): Int = a1 + a2
    }
    val bit = BIT(N)
    var count = 0L
    for {
      j <- 0 until N
    } {
      count += j - bit.sum(1, A(j))
      bit.add(A(j), 1)
    }

    println(count)
  }

}
