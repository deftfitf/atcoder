package jp.atcoder.abc069.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    
    val N, M = sc.nextLong()
    
    // cが足りなければ無理
    // Sを起点, cの余りでSccを作る
    // 下の奴は, O(N)なので 10^12は間に合わない
//    def solve(N: Long, M: Long): Long = {
//      @scala.annotation.tailrec
//      def useS(N: Long, M: Long, scc: Long): Long =
//        if (N > 0) {
//          if (M >= 2) useS(N - 1, M - 2, scc + 1)
//          else scc
//        } else M / 4 + scc
//      useS(N, M, 0)
//    }

    // 以下の奴でO(1)で計算可能
    val r = (((2 * N) min M) / 2) + (((M - (2 * N)) max 0) / 4)

    println(r)
  }

}
