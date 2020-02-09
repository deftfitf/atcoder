package jp.atcoder.abc007.d

object Main {

  def solve(S: String): Long = {
    val L = S.length
    val dp = Array.fill(L + 1, 2, 2)(0L)
    dp(0)(0)(0) = 1
    for {
      i <- 0 until L
      l = S.charAt(i) - '0'
      f <- 0 until 2
      n <- 0 to (if (f == 1) 9 else l)
    } if (f == 1 || n < l) {
      if (n == 4 || n == 9) {
        dp(i + 1)(1)(1) += dp(i)(f)(0)
        dp(i + 1)(1)(1) += dp(i)(f)(1)
      } else {
        dp(i + 1)(1)(0) += dp(i)(f)(0)
        dp(i + 1)(1)(1) += dp(i)(f)(1)
      }
    } else {
      if (n == 4 || n == 9) {
        dp(i + 1)(0)(1) += dp(i)(f)(0)
        dp(i + 1)(0)(1) += dp(i)(f)(1)
      } else {
        dp(i + 1)(0)(0) += dp(i)(f)(0)
        dp(i + 1)(0)(1) += dp(i)(f)(1)
      }
    }
    dp(L)(0)(1) + dp(L)(1)(1)
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B = sc.nextLong()
    val r = solve(B.toString) - solve((A-1).toString)

    println(r)
  }

}