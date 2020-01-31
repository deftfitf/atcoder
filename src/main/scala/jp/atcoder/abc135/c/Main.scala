package jp.atcoder.abc135.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val H = Array.fill(N)(sc.nextInt())
    val dp = new Array[Int](N)

    dp(0) = 0
    var i = 1
    while (i < N) {
      if (i == 1) dp(i) = dp(i-1) + Math.abs(H(i) - H(i+1))
      else
        dp(i) =
          (dp(i-1) + Math.abs(H(i-1) - H(i))) min
            (dp(i-2) + Math.abs(H(i-2) - H(i)))
      i += 1
    }
    println(dp(N-1))
  }

}
