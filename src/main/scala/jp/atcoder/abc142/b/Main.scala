package jp.atcoder.abc142.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val H = Array.fill(N)(sc.nextInt())
    println(H.count(_ >= K))
  }

}
