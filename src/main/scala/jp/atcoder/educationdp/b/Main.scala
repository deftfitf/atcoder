package jp.atcoder.educationdp.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val H = Array.fill(N)(sc.nextInt())
    val Inf = Long.MaxValue / 10
    val dp = Array.fill(N)(Inf)

    dp(0) = 0L
    var i = 0
    while (i < N) {
      var k = 1
      val Upper = N min (i + K + 1)
      while (i + k < Upper) {
        dp(i + k) =
          (dp(i) + Math.abs(H(i) - H(i + k))) min
            dp(i + k)
        k += 1
      }
      i += 1
    }
    println(dp(N-1))
  }

}
