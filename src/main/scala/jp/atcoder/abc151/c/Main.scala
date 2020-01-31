package jp.atcoder.abc151.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val P = new Array[Int](M)
    val S = new Array[Boolean](M)
    (0 until M) foreach { i =>
      P(i) = sc.nextInt() - 1
      S(i) = if (sc.nextLine().trim == "AC") true else false
    }

    def solve(N: Int, M: Int, P: Array[Int], S: Array[Boolean]): (Int, Int) = {
      val solved = new Array[Boolean](N)
      val penalty = new Array[Int](N)
      var count = 0
      var penalties = 0
      for {
        i <- 0 until M
      } if (!solved(P(i)) && S(i)) {
        solved(P(i)) = true
        count += 1
        penalties += penalty(P(i))
      } else {
        penalty(P(i)) += 1
      }
      (count, penalties)
    }

    val (a, b) = solve(N, M, P, S)
    println(s"$a $b")
  }

}
