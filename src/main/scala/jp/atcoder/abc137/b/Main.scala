package jp.atcoder.abc137.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val K, X = sc.nextInt()

    val left = (X - (K - 1)) max -1000000
    val right = (X + (K - 1)) min 1000000

    println((left to right).mkString(" "))
  }

}
