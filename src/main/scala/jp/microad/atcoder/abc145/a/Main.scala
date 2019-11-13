package jp.microad.atcoder.abc145.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B = sc.nextInt()

    println((A - 2 * B) max 0)
  }

}