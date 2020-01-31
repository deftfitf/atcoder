package jp.atcoder.abc148.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextLong()

    // 最終的にできた数 = 10^N * M と表した時の N が答え
    // 10 = 2 * 5
    // 偶数スタートなら偶数しかかからないし
    // 奇数スタートなら奇数しかかからないので
    // 最初から10がかかっている数しかカウントされない
    // 奇数スタートの場合, 常に0個である
    // 偶数スタートの場合, N (<= 10^18) の10の倍数のみを調べ
    // それらを10で割った時の合計が答え
    // だが, 全列挙したところで　10^17 あるので間に合わない
    // N以下の XXXXX00 が何通りあるか？を 調べていく
    // XXXXX 通り ?
//    def solve(N: Long): Long = {
//      if (N % 2 == 1) return 0
//      val NARR: Array[Long] = N.toString.map(_ - '0').map(_.toLong).toArray
//      val L = N.toString.length
//      def loop(i: Int, acm: Long): Long =
//        if (i < L) {
//          loop(i + 1 , acm + (1 until i).foldLeft(NARR(0))((b, n) => b * 10L + NARR(n)))
//        } else acm
//      loop(1, 0)
//    }

    def solve(N: Long): Long = {
      if (N % 2 == 1) return 0
      def loop(denom: Long, acm: Long): Long =
        if (denom <= N) {
          loop(denom * 5, acm + N / denom)
        } else acm
      loop(10, 0)
    }

    println(solve(N))
  }

}
