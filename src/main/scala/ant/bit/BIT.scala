package ant.bit

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