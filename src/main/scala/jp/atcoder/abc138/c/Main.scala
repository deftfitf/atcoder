package jp.atcoder.abc138.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val V = Array.fill(N)(sc.nextDouble()).sorted
    println(V.reduce((x, y) => (x + y) / 2))
  }

}