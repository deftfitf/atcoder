package jp.atcoder.joi2009yo.d

import scala.collection.immutable.Queue

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val M, N = sc.nextInt()
    val A = Array.fill(N, M)(sc.nextInt())

    // つまり, 一筆書きで移動できる距離の最長の長さ
    // 与えられる入力データでは, 移動方法は20万を超えない

    // 90 * 90通り, スタート地点が存在. そこからそれぞれ, dfsを行う
    // 移動方法が20万を超えないのだから, 全ての移動方法について調べればいい
    def solve(N: Int, M: Int, A: Array[Array[Int]]): Int = {
      val vector = List((0, 1), (1, 0), (0, -1), (-1, 0))
      val dist = Array.fill(N, M)(0)
      var max = 0
      def recursive(n: Int, m: Int): Unit =
        for {
          (dn, dm) <- vector
          nn = n + dn
          nm = m + dm
          if nn >= 0 && nn < N && nm >= 0 && nm < M && dist(nn)(nm) == 0 && A(nn)(nm) == 1
        } {
          dist(nn)(nm) = dist(n)(m) + 1
          max = max max dist(nn)(nm)
          recursive(nn, nm)
          dist(nn)(nm) = 0
        }

      for {
        n <- 0 until N
        m <- 0 until M
      } recursive(n, m)
      max
    }

    println(solve(N, M, A))
  }

}
