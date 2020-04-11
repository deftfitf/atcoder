package jp.atcoder.educationdp.second.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, W = sc.nextInt()
    val w, v = new Array[Int](N)
    for (i <- 0 until N) {
      w(i) = sc.nextInt()
      v(i) = sc.nextInt()
    }

    val INF = Int.MaxValue
    val VMAX = 100000
    val dp = Array.fill(N+1, VMAX+1)(INF)
    // dp[i][j] := i品目まで選んだ時の価値の総和がjの時の重さの総和
    dp(0)(0) = 0
    for {
      i <- 0 until N
      j <- 0 to (VMAX - v(i))
    } {
      if (dp(i)(j) != INF && dp(i)(j) + w(i) <= W)
        dp(i+1)(j+v(i)) = dp(i+1)(j+v(i)) min (dp(i)(j) + w(i))
      dp(i+1)(j) = dp(i+1)(j) min dp(i)(j)
    }

    println(dp(N).indices.filter(v => dp(N)(v) != INF).max)
  }

}
