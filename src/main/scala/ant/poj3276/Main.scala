package ant.poj3276

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt(); sc.nextLine()
    val S = (0 until N) map { _ =>
      sc.nextLine()(0)
    }

    def solve(N: Int, S: IndexedSeq[Char]): (Int, Int) = {
      val F = new Array[Int](N+1)
      def inverse(i: Int, M: Int, acm: Int, K: Int): Int =
        if (i <= N) {
          if (acm % 2 == 0)
            if (S(i - 1) == 'B') F(i) = 1
            else F(i) = 0
          else
            if (S(i - 1) == 'B') F(i) = 0
            else F(i) = 1
          inverse(i + 1, M + F(i), acm + F(i) - (if (i - K + 1 >= 0) F(i - K + 1) else 0), K)
        } else M
      (1 to N) map (K => (K, inverse(1, 0, 0, K))) minBy(_._2)
    }

    val (k, m) = solve(N, S)
    println(s"$k $m")
  }

}
