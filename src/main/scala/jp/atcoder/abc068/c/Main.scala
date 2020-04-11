package jp.atcoder.abc068.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val N, M = sc.nextInt()
    val G = Array.fill(N)(Nil: List[Int])
    for (_ <- 0 until M) {
      val u, v = sc.nextInt()
      G(u - 1) ::= (v - 1)
    }

    def solve(N: Int, M: Int, G: Array[List[Int]]): Boolean = {
      val visit = new Array[Boolean](N)
      def dfs(stack: List[(Int, Int)]): Boolean =
        if (stack.nonEmpty) {
          val (depth, u) = stack.head
          if (depth <= 2) {
            if (u == N - 1) {
              true
            } else if (!visit(u)) {
              visit(u) = true
              dfs(
                G(u).foldLeft(stack.tail)(
                  (stack, v) => (depth + 1, v) :: stack))
            } else {
              dfs(stack.tail)
            }
          } else {
            dfs(stack.tail)
          }
        } else {
          false
        }
      dfs((0, 0) :: Nil)
    }

    println(if (solve(N, M, G)) "POSSIBLE" else "IMPOSSIBLE")
  }

}
