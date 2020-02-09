package jp.atcoder.abc154.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val P = Array.fill(N)(sc.nextInt())

    val z = P.take(K).map(p => (p + 1) / 2.0).sum
    val max = (K until N)
      .foldLeft((z, z))((b, i) => {
        val next = b._2 - ((P(i-K) + 1) / 2.0) + ((P(i) + 1) / 2.0)
        (b._1 max next, next)
      })._1

    println(max)
  }

}
