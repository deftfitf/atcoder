package jp.atcoder.abc082.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val r = Array.fill(N)(sc.nextInt())
      .groupBy(identity)
      .foldLeft(0L)((b, n) => {
        if (n._2.length >= n._1) b + (n._2.length - n._1)
        else b + n._2.length
      })

    println(r)
  }

}
