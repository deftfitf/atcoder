package jp.atcoder.abc154.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextLine()
    val L = N.length
    val K = sc.nextInt()

    println(digitDp(N, K, L))
  }

  def digitDp(S: String, K: Int, L: Int): Long = {
    val dp = Array.fill(L+1, 2, 5)(0L)

    dp(0)(0)(0) = 1
    for {
      i <- 0 until L
      d = S.charAt(i) - '0'
      f <- 0 until 2 // N未満が確定している => 1
      n <- 0 to (if (f == 1) 9 else d)
    } if (f == 1 || n < d) {
      if (n != 0) {
        for (j <- 1 until 5)
          dp(i+1)(1)(j) += dp(i)(f)(j-1)
      } else {
        for (j <- 0 until 5)
          dp(i+1)(1)(j) += dp(i)(f)(j)
      }
    } else {
      if (n != 0) {
        for (j <- 1 until 5)
          dp(i+1)(0)(j) += dp(i)(f)(j-1)
      } else {
        for (j <- 0 until 5)
          dp(i+1)(0)(j) += dp(i)(f)(j)
      }
    }

    dp(L)(0)(K) + dp(L)(1)(K)
  }

}
