package jp.atcoder.past.past1.g

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N, N)(0)
    for {
      i <- 0 until N - 1
      j <- i + 1 until N
    } A(i)(j) = sc.nextInt()

    def solve(N: Int, A: Array[Array[Int]]): Int = {
      def loop(i: Int, a: List[Int], b: List[Int], c: List[Int]): Int =
        if (i < N) {
          loop(i + 1, i :: a, b, c) max
          loop(i + 1, a, i :: b, c) max
          loop(i + 1, a, b, i :: c)
        } else {
          calc(a.reverse) +
          calc(b.reverse) +
          calc(c.reverse)
        }
      def calc(members: List[Int]): Int = {
        def loop(members: List[Int], acm: Int): Int =
          if (members.nonEmpty) loop(members.tail, acm + members.map(A(members.head)(_)).sum)
          else acm
        loop(members, 0)
      }
      loop(0, Nil, Nil, Nil)
    }

    println(solve(N, A))
  }

}
