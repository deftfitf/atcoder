package jp.atcoder.abc149.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B, K = sc.nextLong()

    val (a, b) = if (K <= A) {
      (A - K, B)
    } else {
      (0, (B - (K - A) max 0))
    }
    println(s"$a $b")
  }

}
