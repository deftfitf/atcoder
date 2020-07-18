package aoj.DPL.DPL_1.B

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, W = sc.nextInt()
    val v, w = new Array[Int](N)
    for (i <- 0 until N) {
      v(i) = sc.nextInt()
      w(i) = sc.nextInt()
    }

    // dp(i)(w) := i番目の品物まで選んだときに, 重さw以下の場合の最大価値とする
    val dp = Array.fill(N+1, W+1)(-1)
    (0 until N) foreach (i => dp(i)(0) = 0)
    for {
      i <- 0 until N
      j <- 0 to W
    } {
      if (j + w(i) <= W) {
        dp(i+1)(j + w(i)) = dp(i+1)(j + w(i)) max (dp(i)(j) + v(i))
      }
      dp(i+1)(j) = dp(i+1)(j) max dp(i)(j)
    }

    println(dp(N).max)
  }

}
