package jp.microad.atcoder.abc042.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val D = Array.fill(K)(sc.nextInt())

    // N < 10^4 以下なので, 10^5 より小さい数 を使えば必ず買える金額が存在
    // 10^5 より小さい数の空間計算量は用意可能なので
    // [1,10^5)の範囲で {D_1, D_2, ..., D_k}の数が含まれないものだけを列挙する
    // そして N >= となる 最小の金額が達成する金額.

    def solve(N: Int, K: Int, D: Array[Int]): Int = {
      val MAX_DIGIT = 5
      val UPPER = 100000
      val LIKES = (0 to 9) filter (!D.contains(_))
      def dfs(stack: List[(Int, Int)], min: Int): Int =
        if (stack.nonEmpty) {
          val (digit, num) = stack.head
          if (digit <= MAX_DIGIT)
            dfs(LIKES.foldLeft(stack.tail)((stack, d) =>
              (digit + 1, num * 10 + d) :: stack), if (num >= N) min min num else min)
          else dfs(stack.tail, min)
        } else min
      dfs(LIKES.filter(_ != 0).map((1, _)).toList, UPPER)
    }

    println(solve(N, K, D))
  }

}
