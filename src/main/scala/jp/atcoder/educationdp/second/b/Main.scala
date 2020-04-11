package jp.atcoder.educationdp.second.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val H = Array.fill(N)(sc.nextInt())

    val INF = Int.MaxValue
    val dp = Array.fill(N)(INF)

    dp(0) = 0
    for {
      i <- 0 until N - 1
      k <- 1 to (K min (N - i - 1))
    } dp(i+k) = dp(i+k) min (dp(i) + (H(i)-H(i+k)).abs)

    println(dp(N-1))
  }

}
