package jp.microad.atcoder.abc149.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val M = sc.nextLong()
    val A = Array.fill(N)(sc.nextLong()).sorted(Ordering.Long.reverse)
    // 左手と右手で握手できる.
    // その時, 左手で握手した人のパワー + 右手で握手した人のパワーが上昇する
    // しかし, 全く同じ握手を二回以上行えない,
    // 右手と左手を入れ替えて握手することは可能, また, 両手で握手することも可能
    // パワーが大きい順に貪欲に握手していけば良さそうな気がする
    // まず、パワーが大きい順にソート.
    // 左から, 両手で握手して, 左手で握手,
    // ただ, Mが10^5なので, このままでは間に合わない
    // 握手を行う人と, 握手を行わない人を分別する必要がある
    // 二分探索でその境界を探す
    // 握手を行う人の, 握手の組み合わせの数, 「握手する人から2つを選ぶ数」がM以上になる境界
    // 「握手する人から2つを選ぶ数」は, 「握手をする人 * 握手をする人」通りある
    // そしてその仮定の素では、選んだ全ての人と1 + 2 * (L - 1)回握手することになる, 両手は一回, 他の人と左手, 右手
    // そして探したM以上になるやつで, Mになるまで小さい順から握手を減らしていけばいい
    // うーーん、違うか
//    def solve(N: Int, M: Long, A: Array[Long]): Long = {
//      // 組み合わせの数が M 以上になる握手人数
//      def lowerBounds(M: Long): Int = {
//        def loop(left: Int, right: Int): Int =
//          if (right - left > 1) {
//            val mid = left + (right - left) / 2
//            if (mid * mid >= M) loop(left, mid)
//            else loop(mid, right)
//          } else right
//        loop(-1, N)
//      }
//      val L = lowerBounds(M)
//      val MAXP = (0 until L).map(i => A(i)).sum * (2 * (L - 1) + 1)// 最大数
//      val AA = A.take(L).sorted // 対象にしているやつのみ選び昇順にソートする
//
//      println(MAXP, L)
//      def loop(i: Int, rest: Long, acm: Long): Long =
//        if (rest > M) {
//          if (M - rest >= 3) {
//            loop(i + 1, rest - 3, acm - 4 * AA(i) - 2 * AA(i+1))
//          } else if (M - rest >= 2) {
//            loop(i + 1, rest - 2, acm - 3 * AA(i) - 1 * AA(i+1))
//          } else {
//            loop(i + 1, rest - 1, acm - 2 * AA(i))
//          }
//        } else acm
//      loop(0, L, MAXP)
//    }

    // 単に全ての組み合わせの中から幸福度が高い順番にM個取り出せば良いが、間に合わない
    // 組み合わせの中から高い順にM個取り出せばいい、ということは,
    // 幸福度が上がる量が X 以上の 組み合わせが M通り以上ある場合の最小のXについて求める
    // 左手で握手する人を決めた場合、右手で握手できる人が何人いるか？
    // 累積和のくだりよくわからんので, 左手で握手する人を決め,
    // 右手で握手できる人数を二分探索で求めることにする
    // 先に大きい順に並び変えておく. O(NlogNlogN)で求まるはず.
    // そのXが求まった場合, それを満たす場合の計算をする
    def solve(N: Int, M: Long, A: Array[Long]): Long = {
      val AMAX = Math.pow(10, 5).toInt * 2
      def lowerBounds(M: Long): Long = {
        def loop(left: Long, right: Long): Long =
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (check(mid)) loop(mid, right)
            else loop(left, mid)
          } else left
        loop(0, AMAX+1)
      }
      def check(X: Long): Boolean =
        (0 until N).foldLeft(0L)((acm, l) => {
          val r = lowerBounds2(X, l)
          acm + (r + 1)
        }) >= M
      // lを決めた時に,X以上にできる数のindex, = lの時見つからなかかった
      def lowerBounds2(X: Long, l: Int): Int = {
        def loop(left: Int, right: Int): Int =
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (A(l) + A(mid) >= X) loop(mid, right)
            else loop(left, mid)
          } else left
        loop(-1, N)
      }

      val R = new Array[Long](N)
      R(0) = A(0)
      (1 until N).foreach(i => {
        R(i) += A(i) + R(i-1)
      })
      val X = lowerBounds(M)
      // M回を超える場合があるので, 超えた分を X * 超えた分引く必要がある
      val over = (0 until N).foldLeft(0L)((acm, l) => {
        val r = lowerBounds2(X, l)
        acm + (r + 1)
      }) - M
      (0 until N).foldLeft(0L)((acm, l) => {
        val r = lowerBounds2(X, l)
        if (r >= 0) {
          acm + R(r) + A(l) * (r + 1)
        } else acm
      }) - (if (over >= 0) over * X else 0)
    }

    println(solve(N, M, A))
  }

}
