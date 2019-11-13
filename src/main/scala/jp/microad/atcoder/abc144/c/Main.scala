package jp.microad.atcoder.abc144.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextLong()
    val upper = Math.sqrt(N)
    val candidates = for {
      i <- (1L to upper.toLong)
      if N % i == 0
      j = N / i
    } yield i + j - 2

    val min = candidates.min
    println(min)
  }

}
