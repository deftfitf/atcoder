package jp.atcoder.abc133.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, D = sc.nextInt()
    val X = Array.fill(N, D)(sc.nextInt())

    val res =for {
      i <- 0 until (N - 1)
      j <- i + 1 until N
    } yield {
      val sum = X(i).zip(X(j)).foldLeft(0: Double) { case (b, n) =>
        b + Math.pow(n._1 - n._2, 2)
      }
      val sqrt = Math.sqrt(sum)
      sqrt % 1 == 0
    }
    println(res.count(identity))
  }

}
