package jp.atcoder.arc054.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val P = sc.nextDouble()
    // T(334) を計算したい, 現代のコンピュータではP年かかる
    // ムーアの法則, 1.5年ごとに2倍になる
    // x年後には. 2^(x/1.5)倍になる
    // 適切なタイミングで計算を始めることでT(334)の計算を早く終わらせたい
    // 計算を始めるまでの時間 + 計算を始めた時点でのコンピュータでT(334)を計算するのにかかる時間
    // 計算が終わるまでの最短の時間を求めてください
    // x年後に計算をはじめるとき、計算が終わるまでの時間は
    // f(x) = x + P/2^(x/1.5) 年 である
    // これは凸関数なので, 凸関数の最小値を求めることになる
    // 三分探索は, 右端と左端の区間の長さの三分の一を狭めていく
    // (r - l) / 3 + l, 2 * (r - l) / 3 + l
    // より大きい方を縮める事になる
    def ternarySearch(f: Double => Double, lower: Double, upper: Double): Double = {
      def recursive(i: Int, l: Double, r: Double): Double =
        if (i < 500) {
          val c1 = (2.0 * l + r) / 3.0
          val c2 = (l + 2.0 * r) / 3.0
          if (f(c1) > f(c2)) recursive(i + 1, c1, r)
          else recursive(i + 1, l, c2)
        } else l
      f(recursive(0, lower, upper))
    }

    println(ternarySearch(x => x + (P / Math.pow(2, x / 1.5)), 0d, Math.pow(10, 18)))
  }

}
