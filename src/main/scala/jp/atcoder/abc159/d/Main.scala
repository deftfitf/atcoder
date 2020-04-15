package jp.atcoder.abc159.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    val NC = new Array[Int](N+1)
    val NCOMB = new Array[Long](N+1)

    for {
      i <- 0 until N
    } {
      NC(A(i)) += 1
    }

    NCOMB(0) = 0
    NCOMB(1) = 0
    NCOMB(2) = 1
    for {
      i <- 3 to N
    } {
      NCOMB(i) = i * NCOMB(i - 1) / (i - 2)
    }

    val S = (for {
      a <- A.toSet[Int].toSeq
    } yield NCOMB(NC(a))).sum

    val ans = for {
      i <- 0 until N
    } yield {
      S - NCOMB(NC(A(i))) + NCOMB(NC(A(i)) - 1)
    }

    println(ans.mkString("\n"))
  }

}
