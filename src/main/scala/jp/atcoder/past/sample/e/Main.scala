package jp.atcoder.past.sample.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val _, M = sc.nextInt()
    val P, Y = new Array[Int](M)
    val range = 0 until M
    range.foreach { i =>
      val p, y = sc.nextInt()
      P(i) = p
      Y(i) = y
    }

    val rank = new Array[Int](M)
    val seq = range.sortBy(i => (P(i), Y(i)))
    var i = 0
    var count = 0
    var before = -1
    while (i < M) {
      val j = seq(i)
      if (before != P(j))
        count = 1
      before = P(j)
      rank(j) = count
      count += 1
      i += 1
    }

    val R = range.map(i => "%06d%06d".format(P(i), rank(i))).mkString("\n")

    println(R)
  }

}
