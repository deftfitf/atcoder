package jp.atcoder.abc142.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextDouble()
    println(Math.ceil(N / 2.0) / N)
  }

}
