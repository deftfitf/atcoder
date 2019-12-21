package jp.microad.atcoder.abc045.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine().map(_ - '0')
    // 入れる入れないを列挙する
    // |S| - 1 個の間で 入れる 入れない を選ぶ
    // 2^(|S| - 1) 通り
    def solve(S: IndexedSeq[Int]): Long = {
      val L = S.length - 1
      @scala.annotation.tailrec
      def dfs(stack: List[(Int, List[Int])], acm: Long): Long =
        if (stack.nonEmpty) {
          val (i, lst) = stack.head
          if (i < L) dfs((i + 1, lst) :: (i + 1, i :: lst) :: stack.tail, acm)
          else dfs(stack.tail, acm + calc(lst.reverse))
        } else acm
      def calc(lst: List[Int]): Long = {
        val N = S.length
        @scala.annotation.tailrec
        def loop(i: Int, lst: List[Int], num: Long, acm: Long): Long =
          if (i < N) {
            if (lst.nonEmpty) {
              if (i <= lst.head) loop(i + 1, lst, 10 * num + S(i), acm)
              else loop(i, lst.tail, 0, acm + num)
            } else loop(i + 1, lst, 10 * num + S(i), acm)
          } else acm + num
        loop(1, lst, S(0), 0)
      }
      dfs((0, Nil) :: Nil, 0)
    }

    println(solve(S))
  }

}
