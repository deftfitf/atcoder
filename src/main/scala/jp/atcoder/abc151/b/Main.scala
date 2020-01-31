package jp.atcoder.abc151.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K, M = sc.nextInt()
    val MIN = M * N
    val A = Array.fill(N-1)(sc.nextInt())
    val S = A.sum

    println((0 to K).find(last => (S + last) >= MIN).getOrElse(-1))
  }

}
