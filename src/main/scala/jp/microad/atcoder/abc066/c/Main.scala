package jp.microad.atcoder.abc066.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // 偶数の時, 同じ向き
    // 奇数の時, 逆向き
    // 0から始まる時, 偶数の時, 後ろにつける, 奇数の時, 前に付ける
    def solve(N: Int, A: Array[Int]): Vector[Int] = {
      @scala.annotation.tailrec
      def loop(i: Int, sink: Vector[Int]): Vector[Int] =
        if (i < N) {
          if (i % 2 == 0) loop(i + 1, sink :+ A(i))
          else loop(i + 1, A(i) +: sink)
        } else {
          if (N % 2 == 0) sink
          else sink.reverse
        }
      loop(0, Vector.empty)
    }

    println(solve(N, A).mkString(" "))
  }

}
