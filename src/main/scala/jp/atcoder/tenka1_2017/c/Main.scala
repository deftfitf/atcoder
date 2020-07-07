package jp.atcoder.tenka1_2017.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    // 制約から, h, n, w <= 3500を探索することになる
    val (h, n, w) = (for {
      h <- 1L to 3500L
      n <- 1L to 3500L
      top = N * h * n
      bottom = 4L * h * n - N * n - N * h
      if bottom > 0
      if top % bottom == 0
      w = top / bottom
    } yield (h, n, w)).head

    println(s"$h $n $w")
  }

}
