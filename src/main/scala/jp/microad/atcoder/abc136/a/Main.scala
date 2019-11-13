package jp.microad.atcoder.abc136.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B, C = sc.nextInt()

    println((C - (A - B)) max 0)
  }

}
