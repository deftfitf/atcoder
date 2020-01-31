package jp.atcoder.abc148.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())
    // K 個のレンガを砕いて, (N - 1以下)
    // 残ったレンガを 1 ~ K になるように並べたい
    // 最後まで見て行った時の最大を N から引いた数
    def solve(N: Int, A: Array[Int]): Int = {
      def loop(i: Int, j: Int): Int =
        if (i < N) {
          if (A(i) == j + 1) loop(i + 1, j + 1)
          else loop(i + 1, j)
        } else j
      val j = loop(0, 0)
      if (j == 0) -1
      else N - j
    }

    println(solve(N, A))
  }

}
