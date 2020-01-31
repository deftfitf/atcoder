package jp.atcoder.abc142.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    println((0 until N) sortBy A map (_ + 1) mkString(" "))
  }

}