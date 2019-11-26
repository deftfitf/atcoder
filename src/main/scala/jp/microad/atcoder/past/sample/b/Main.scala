package jp.microad.atcoder.past.sample.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val h = Array.fill(N)(sc.nextInt())

    val num = h.count(_ >= K)
    println(num)
  }

}
