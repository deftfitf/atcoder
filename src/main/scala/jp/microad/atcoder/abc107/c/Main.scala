package jp.microad.atcoder.abc107.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val X = Array.fill(N)(sc.nextInt())

    // K個のろうそく の距離 + どちらかの端点への距離 が最小のもの
    def solve(N: Int, K: Int, X: Array[Int]): Int = {
      val upper = N - K
      def loop(i: Int, min: Int): Int =
        if (i <= upper) {
          val width = X(i + K - 1) - X(i)
          val toStart = X(i + K - 1).abs min X(i).abs
          loop(i + 1, min min (width + toStart))
        } else min
      loop(0, Int.MaxValue)
    }

    println(solve(N, K, X))
  }

}
