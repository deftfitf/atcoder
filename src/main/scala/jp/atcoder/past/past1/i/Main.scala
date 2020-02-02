package jp.atcoder.past.past1.i

import java.util.regex.Pattern

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val S = new Array[Int](M)
    val C = new Array[Int](M)
    val pattern = Pattern.compile("[YN]+")
    (0 until M) foreach { i =>
      S(i) = sc.next(pattern).zipWithIndex
        .foldLeft(0)((bit, next) =>
          bit | (if (next._1 == 'Y') 1 << next._2 else 0))
      C(i) = sc.nextInt()
    }

    // パーツを買う,買わないの全探索をすると間に合わない.
    // ここで, 全状態をdpで管理する
    // dp[i] := 状態iの時に必要な最小金額 とすると
    // dp[i | S_i] := (dp[i] + C_i) min dp[i] として更新可能
    def solve(N: Int, M: Int, S: Array[Int], C: Array[Int]): Option[Long] = {
      val COMPLETED = (0 until N).foldLeft(0)((bit, next) => bit | (1 << next))
      val dp = Array.fill(COMPLETED+1)(Long.MaxValue)
      dp(0) = 0
      for {
        s <- 0 until COMPLETED
        j <- 0 until M
      } if (dp(s) != Long.MaxValue)
        dp(s | S(j)) = (dp(s) + C(j)) min dp(s | S(j))
      if (dp(COMPLETED) != Long.MaxValue) Some(dp(COMPLETED))
      else None
    }

    println(solve(N, M, S, C).getOrElse(-1))
  }

}
