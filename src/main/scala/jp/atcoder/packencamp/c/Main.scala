package jp.atcoder.packencamp.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val A = Array.fill(N, M)(sc.nextInt())

    val max = (for {
      t1 <- 0 until (M - 1)
      t2 <- (t1 + 1) until M
      p = (0 until N).map(i => A(i)(t1) max A(i)(t2)).map(_.toLong).sum
    } yield p).max

    println(max)
  }

}
