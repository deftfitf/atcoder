package jp.microad.atcoder.abc054.c

import scala.collection.mutable

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val G = Array.fill(N+1)(List[Int]())
    (0 until M) foreach { _ =>
      val u, v = sc.nextInt()
      G(u) ::= v
      G(v) ::= u
    }
    // 1からスタートして, dfsする
    // 今通過している頂点情報を持っておく,
    // 頂点数がNになった場合にはカウントを増やす
    // 頂点を複数回訪れることはOK
    // しかし, 辺は複数回訪れてはいけない
    // 辺を訪れたかどうかの情報を保持しておく

    def solve(N: Int, M: Int, G: Array[List[Int]]): Int = {
      val visiting = mutable.Set[Int]()
      @scala.annotation.tailrec
      def dfs(stack: List[(Int, Int)], count: Int): Int =
        if (stack.nonEmpty) {
          val (t, u) = stack.head
          if (u < 0) {
            visiting -= -u
            dfs(stack.tail, count)
          } else {
            visiting += u
            if (visiting.size >= N) {
              visiting -= u
              dfs(stack.tail, count + 1)
            } else {
              dfs(G(u).foldLeft((t, -u) :: stack.tail)(
                (b, v) => if (!visiting.contains(v)) (u, v) :: b else b), count)
            }
          }
        } else count

      dfs((1, 1) :: Nil, 0)
    }

    println(solve(N, M, G))
  }

}
