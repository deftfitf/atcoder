package jp.atcoder.abc007.d

@annotation.DP.DigitDP
object DigitDP {

  def main(args: Array[String]): Unit = {
    val S = "50030959"
    val L = S.length
    val dp = Array.fill(L+1, 2)(0)

    dp(0)(0) = 1
    for {
      i <- 0 until L
      d = S.charAt(i) - '0'
      f <- 0 until 2 // 0 => N未満であることが確定していない, 1 => 確定している
      n <- 0 to (if (f == 1) 9 else d)
    } if (f == 1 || n < d) {
      dp(i+1)(1) += dp(i)(f)
    } else {
      dp(i+1)(0) += dp(i)(f)
    }

    println(dp(L)(0)+dp(L)(1))
  }

}
