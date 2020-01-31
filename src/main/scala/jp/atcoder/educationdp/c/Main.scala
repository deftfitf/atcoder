package jp.atcoder.educationdp.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val Inf = Int.MinValue / 10
    val A, B, C = Array.fill(N)(Inf)
    (0 until N) foreach { i =>
      val a, b, c = sc.nextInt()
      A(i) = a
      B(i) = b
      C(i) = c
    }

    val dp = Array.fill(N+1, 3)(Inf)
    dp(0)(0) = 0
    dp(0)(1) = 0
    dp(0)(2) = 0
    var i = 0
    while (i < N) {
      dp(i+1)(0) = (dp(i)(1) + A(i)) max (dp(i)(2) + A(i))
      dp(i+1)(1) = (dp(i)(0) + B(i)) max (dp(i)(2) + B(i))
      dp(i+1)(2) = (dp(i)(0) + C(i)) max (dp(i)(1) + C(i))
      i += 1
    }
    println(dp(N).max)
  }

}
