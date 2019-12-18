package ant.segmenttree

/**
  * セグメント木によるRMQ
  * @param N 葉のサイズ
  * @param nodes ノード配列
  */
class RMQ(N: Int, nodes: Array[Int]) {

  /**
    * 与えられた半開区間の最小値を求める
    * @param s 開始区間
    * @param t 終了区間
    * @return [s, t)の最小値
    */
  def query(s: Int, t: Int): Int = {
    @scala.annotation.tailrec
    def loop(stack: List[(Int, Int, Int)], min: Int): Int =
      if (stack.nonEmpty) {
        val (k, left, right) = stack.head
        if (right <= s || left >= t) loop(stack.tail, min)
        else if (left >= s && right <= t) loop(stack.tail, nodes(k) min min)
        else loop((2 * k + 1, left, (left + right) / 2) :: (2 * k + 2, (left + right) / 2, right) :: stack.tail, min)
      } else min
    loop((0, 0, N) :: Nil, RMQ.UNIT)
  }

  /**
    * k番目の葉の値をaに更新し, セグメント木が保持するノードの値も更新する
    * @param k 葉の番号
    * @param a 葉の値
    */
  def update(k: Int, a: Int): Unit = {
    val leaf = N + k - 1
    nodes(leaf) = a
    @scala.annotation.tailrec
    def loop(i: Int): Unit =
      if (i > 0) {
        val parent = (i - 1) / 2
        nodes(parent) = nodes(2 * parent + 1) min nodes(2 * parent + 2)
        loop(parent)
      }
    loop(leaf)
  }

  override def toString: String =
    nodes.indices.mkString("\t") + "\n" +
    nodes.mkString("\t")

}

object RMQ {

  private def leafSizeOf(elems: Array[Int]): Int = {
    val N = elems.length
    @scala.annotation.tailrec
    def loop(size: Int): Int =
      if (size < N) loop(size << 1)
      else size
    loop(1)
  }

  private val UNIT = Int.MaxValue

  def apply(elems: Array[Int], unit: Int = UNIT): RMQ = {
    val N = leafSizeOf(elems)
    val nodes = Array.fill(2 * N - 1)(unit)
    val rmq = new RMQ(N, nodes)
    elems.zipWithIndex.foreach { case (a, k) =>
      rmq.update(k, a)
    }
    rmq
  }

}

object Main {

  def main(args: Array[String]): Unit = {
    val rmq = RMQ(Array(5, 3, 7, 9, 1, 4, 6, 2))
    println(rmq.query(3, 4))
  }

}
