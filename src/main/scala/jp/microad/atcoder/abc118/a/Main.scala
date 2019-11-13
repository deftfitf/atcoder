package jp.microad.atcoder.abc118.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B = sc.nextInt()
    if (B % A == 0)
      println(A + B)
    else
      println(B - A)
  }

}
