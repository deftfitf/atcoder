package jp.atcoder.abc133.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val D = Array.fill(N)(sc.nextInt()).sorted

    val center = N / 2
    println(D(center) - D(center - 1))
  }

}
