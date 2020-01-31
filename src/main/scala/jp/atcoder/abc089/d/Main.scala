package jp.atcoder.abc089.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W, D = sc.nextInt()
    val AtoIJ = new Array[(Int, Int)](H * W + 1)
    for {
      i <- 1 to H
      j <- 1 to W
    } {
      val A = sc.nextInt()
      AtoIJ(A) = (i, j)
    }
    val Q = sc.nextInt()
    val L, R = new Array[Int](Q)
    (0 until Q) foreach { i =>
      val l, r = sc.nextInt()
      L(i) = l
      R(i) = r
    }

    // H x W までの数が重複なく書かれている
    // 移動量だけ魔力を消費してマスに置かれた駒を移動させる
    // 整数Liの書かれているマスに置かれている
    // Li + nD が = Riになるまで移動を行う
    // Ai,j => (i, j) が O(1) で導出できたとして,
    // 愚直に全部計算した場合どうなる...
    // D = 1の場合, 最悪計算量 O(HWQ) ? 300 x 300 x 10^5 = 9 * 10^9
    // 最良の場合 全部 L_i = R_i で O(Q). ならしても O(HQ) とかで普通にやるとしんどそう
    // Dが固定というのがポイントそう.
    // Dが固定ということは, ある 1 <= L <= R <= H x W について
    // 共有している区間は全て同じ魔力を使用するということ
    // また, D だけスライドする
    // 先に消費魔力テーブルを用意しておく.
    // 答えは dp[R_i] - dp[L_i]
    def solve(): IndexedSeq[Long] = {
      val dp = new Array[Long](H * W + 1)
      for {
        d <- 1 to D
        i <- (d + D) to (H * W) by D
      } {
        val (x1, y1) = AtoIJ(i - D)
        val (x2, y2) = AtoIJ(i)
        dp(i) = dp(i - D) + (x2 - x1).abs.toLong + (y2 - y1).abs.toLong
      }

      (0 until Q).map(q => dp(R(q)) - dp(L(q)))
    }

    println(solve().mkString("\n"))
  }

}
