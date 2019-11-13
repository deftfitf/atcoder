package jp.microad.atcoder.abc145.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val d = Array.fill(N)(sc.nextInt())

    val res = for {
      i <- (0 until N-1)
      j <- (i+1 until N)
    } yield d(i) * d(j)
    println(res.sum)
  }

}
