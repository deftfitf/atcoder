package jp.atcoder.abc138.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextDouble())

    println(1 / A.map(1 / _).sum)
  }

}
