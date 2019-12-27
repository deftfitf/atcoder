package jp.microad.atcoder.abc015.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val T = Array.fill(N, K)(sc.nextInt())
    // 全探索すれば良さそうだが
    // XORが結合則を満たしていれば...
    // K^N, 5^5 = 3125 しかないので

    def solve(N: Int, K: Int, T: Array[Array[Int]]): Boolean = {
      @scala.annotation.tailrec
      def dfs(stack: List[(Int, Int)]): Boolean =
        if (stack.nonEmpty) {
          val (i, n) = stack.head
          if (i < N) {
            dfs((0 until K).map(k => T(i)(k))
              .foldLeft(stack.tail)(
                (stack, t) => (i + 1, n ^ t) :: stack))
          } else {
            if (n == 0) false
            else dfs(stack.tail)
          }
        } else true

      dfs((0 until K).map(k => T(0)(k))
        .foldLeft(Nil: List[(Int, Int)])((stack, t) => (1, t) :: stack))
    }

    if (solve(N, K, T)) {
      println("Nothing")
    } else {
      println("Found")
    }
  }

}
