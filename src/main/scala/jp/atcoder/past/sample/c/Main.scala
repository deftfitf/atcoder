package jp.atcoder.past.sample.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, T = sc.nextInt()
    val c, t = new Array[Int](N)
    (0 until N).foreach { i =>
      val _c, _t = sc.nextInt()
      c(i) = _c
      t(i) = _t
    }

    val candidates = (0 until N).filter(i => t(i) <= T)
    val R = if (candidates.isEmpty) "TLE" else c(candidates.minBy(i => c(i))).toString
    println(R)
  }

}
