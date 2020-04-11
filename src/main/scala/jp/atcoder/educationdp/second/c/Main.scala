package jp.atcoder.educationdp.second.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A, B, C = new Array[Int](N)
    for (i <- 0 until N) {
      val a, b, c = sc.nextInt()
      A(i) = a
      B(i) = b
      C(i) = c
    }

    val dp = Array.fill(3, N+1)(0)
    dp(0)(0) = 0
    dp(1)(0) = 0
    dp(2)(0) = 0
    for {
      i <- 1 until N + 1
    } {
      dp(0)(i) = (dp(1)(i-1) + B(i-1)) max (dp(2)(i-1) + C(i-1))
      dp(1)(i) = (dp(0)(i-1) + A(i-1)) max (dp(2)(i-1) + C(i-1))
      dp(2)(i) = (dp(0)(i-1) + A(i-1)) max (dp(1)(i-1) + B(i-1))
    }

    println(dp(0)(N) max dp(1)(N) max dp(2)(N))
  }

}
