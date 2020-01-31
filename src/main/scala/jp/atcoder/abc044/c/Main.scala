package jp.atcoder.abc044.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, A = sc.nextInt()
    val X = Array.fill(N)(sc.nextInt())

    // N <= 16のデータセットの場合は, 単に 2^16 通りなので
    // 全探索しても間に合うが...
    // dp[i][j][s] := i + 1枚目までのカードの中でカードをj枚選んだ時, 合計がsとなる場合の数 とする
    def solve(N: Int, A: Int, X: Array[Int]): Long = {
      val dp = Array.fill(N + 1, N + 1, 50 * 50 + 1)(0L)
      dp(0)(0)(0) = 1L
      for {
        i <- 1 to N
        j <- 0 to i
        s <- 0 to 2500
      } if (j >= 1 && s >= X(i-1)) dp(i)(j)(s) = dp(i - 1)(j - 1)(s - X(i-1)) + dp(i - 1)(j)(s)
        else dp(i)(j)(s) = dp(i - 1)(j)(s)
      (1 to N).map(i => dp(N)(i)(A * i)).sum
    }

    println(solve(N, A, X))
  }

}
