package jp.atcoder.educationdp.second.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val h = Array.fill(N)(sc.nextInt())

    // dp[i] := iの足場にたどり着くまでに支払うコストの総和の最小値
    val INF = Int.MaxValue
    val dp = Array.fill(N)(INF)
    dp(0) = 0
    for (i <- 0 until N - 1) {
      dp(i + 1) = dp(i + 1) min (dp(i) + (h(i) - h(i + 1)).abs)
      if (i < N - 2) {
        dp(i + 2) = dp(i + 2) min (dp(i) + (h(i) - h(i + 2)).abs)
      }
    }

    println(dp(N-1))
  }

}
