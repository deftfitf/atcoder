package jp.atcoder.abc120.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B, C = sc.nextInt()

    println((B / A) min C)
  }

}
