package jp.atcoder.abc084.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val MAX = Math.pow(10, 5).toInt
    val Q = sc.nextInt()
    val L, R = new Array[Int](Q)
    (0 until Q) foreach { i =>
      val l, r = sc.nextInt()
      L(i) = l
      R(i) = r
    }

    def eratosthenes(n: Int): Seq[Int] = {
      val upper = Math.sqrt(n)
      def loop(lst: List[Int], used: List[Int]): List[Int] =
        if (lst.head < upper) {
          loop(lst.tail.filter(_ % lst.head != 0), lst.head :: used)
        } else used.reverse ++ lst
      loop((2 to n).toList, Nil)
    }

    val isPrime = new Array[Boolean](MAX + 1)
    eratosthenes(MAX).foreach { prime =>
      isPrime(prime) = true
    }

    val dp = new Array[Int](MAX + 1)
    (1 to MAX) foreach { n =>
      dp(n) = if (isPrime(n) && isPrime((n + 1) / 2)) dp(n - 1) + 1 else dp(n - 1)
    }

    val r = (0 until Q).map { i =>
      dp(R(i)) - dp(L(i) - 1)
    }. mkString("\n")

    println(r)
  }

}
