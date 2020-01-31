package jp.atcoder.abc123.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = Array.fill(5)(sc.nextInt())
    val K = sc.nextInt()
    val r = N.combinations(2).exists { case Array(a, b) => Math.abs(a - b) > K }
    if (r) println(":(") else println("Yay!")
  }

}
