package jp.microad.atcoder.educationdp.i

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val P = Array.fill(N)(sc.nextDouble())

    val dp = Array.fill(N+1, N+1)(0d)

    dp(0)(0) = 1

    var i = 0
    while (i < N) {
      var j = 0
      while (j < N) {
        dp(i+1)(j+1) += dp(i)(j) * P(i)
        dp(i+1)(j)   += dp(i)(j) * (1 - P(i))
        j += 1
      }
      i += 1
    }

    println(dp(N).zipWithIndex.filter(_._2 > N / 2).map(_._1).sum)
  }

}
