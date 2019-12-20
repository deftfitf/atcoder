package jp.microad.atcoder.arc066.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    def solve(N: Int, A: Array[Int]): Long = {
      val MAX = N - 1
      val C = new Array[Int](N)
      (0 to MAX) foreach { n =>
        C(((MAX - n) - n).abs) += 1
      }

      val INF = Math.pow(10, 9).toLong + 7.toLong
      def ans(n: Int, acm: Long): Long =
        if (n < N / 2) ans(n + 1, (acm * 2L) % INF)
        else acm

      def find(i: Int): Long =
        if (i < N) {
          C(A(i)) -= 1
          find(i + 1)
        } else {
          if (C.forall(_ == 0)) ans(0, 1L)
          else 0
        }

      find(0)
    }

    println(solve(N, A))
  }

}
