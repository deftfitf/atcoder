package jp.atcoder.abc084.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val C, S, F = new Array[Int](N - 1)
    (0 until N - 1) foreach { i =>
      val c, s, f = sc.nextInt()
      C(i) = c
      S(i) = s
      F(i) = f
    }

    def solve(N: Int, C: Array[Int], S: Array[Int], F: Array[Int]): Seq[Int] = {
      @scala.annotation.tailrec
      def loop(i: Int, time: Int): Int =
        if (i < N - 1) {
          if (time < S(i)) loop(i + 1, S(i) + C(i))
          else if (time % F(i) == 0) loop(i + 1, time + C(i))
          else loop(i + 1, F(i) * (time / F(i) + 1) + C(i))
        } else time
      (0 until N) map (i => loop(i, 0))
    }

    println(solve(N, C, S, F).mkString("\n"))
  }

}
