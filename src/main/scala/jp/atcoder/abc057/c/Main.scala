package jp.atcoder.abc057.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextLong()

    // 約数列挙するのに O(√N) で間に合う
    // 列挙した約数の個数 M <= √N なので
    // 約数ごとに F(A, B) を 調べ その最小値を求める
    // O(M + √N) = O(√N). 間に合う

    def solve(N: Long): Int = {
      val upper = Math.sqrt(N)

      def F(a: Long, b: Long): Int =
        a.toString.length max b.toString.length

      def loop(a: Long, min: Int): Int =
        if (a <= upper) {
          if (N % a == 0) loop(a + 1, min min F(a, N / a))
          else loop(a + 1, min)
        } else min

      loop(1, 10)
    }

    println(solve(N))
  }

}
