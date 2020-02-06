package ant.bit

import java.util.regex.Pattern

object POJ3468 {

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

    val N, Q = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())
    val pattern = Pattern.compile("[CQ]")

    // 差分を管理する
    // 各節点は, 次の2つのデータを持つようにする
    // その節点の区間に一様でなく加えられた値の和
    // 一様に区あわられた値を区別することで
    // 区間への加算が効率的に行えるようになる
    // 親の節点に一様に加えられた場合, 子の節点にはそれを換算しない
    // 再帰的に和を計算する際に、その分をその節点で加算するようにします
    // これで、加算と計算の両方のクエリにO(log n)で答えることができます

    implicit val intGroup: Group[Int] = new Group[Int] {
      override def inverse(a: Int): Int = -a
      override def zero: Int = 0
      override def op(a1: Int, a2: Int): Int = a1 + a2
    }
    val bit0, bit1 = BIT(N)
    A.zipWithIndex.foreach { case (a, i) =>
      bit0.add(i+1, a)
    }

    for {
      _ <- 0 until Q
    } {
      sc.next(pattern) match {
        case "C" =>
          val a, b, c = sc.nextInt()
          bit0.add(a, -c * (a-1))
          bit0.add(b, c * b)
          bit1.add(a, c)
          bit1.add(b+1, -c)
        case "Q" =>
          val a, b = sc.nextInt()
          val r =
            (b * bit1.sum(b) + bit0.sum(b)) -
              ((a - 1) * bit1.sum(a - 1) + bit0.sum(a - 1))
          println(r)
      }
    }
  }

}
