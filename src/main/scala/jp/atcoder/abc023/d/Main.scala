package jp.atcoder.abc023.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val H, S = new Array[Int](N)
    for (i <- 0 until N) {
      H(i) = sc.nextInt()
      S(i) = sc.nextInt()
    }

    // 風船iの競技開始時の高度Hi, 1秒あたりの高度の増加量Si
    // 最初に1個風船を割ることができ, 1秒ごとに1個の風船を割ることができる
    // どの順番で風船を割るかは高橋くんが自由に決定できる
    // 風船を割るとペナルティ, ペナルティは高度が高くなればなるほど高くなる
    // 高橋くんが最終的に得る特典は, N個のペナルティのうちの最大値となる
    // ペナルティの最小値を求めよ
    // 1秒ごとに風船を割ることができるので, 最後の風船を割る時には, (N - 1)秒経過している
    // つまり, ある風船を最後に割る時には, Hi + Si * (N - 1) の高度になっているはず
    // 直感的には, ある風船を割るタイミングは, その時点で最も高い風船を割れば良さそう
    //
    // 最大値の最小化 を行う
    // f(x) := 最大値をxにできるかどうか?と仮定する できるものの最小値を求める
    // 最大値をxにできるかどうかを判断する時, 全ての風船を, 高度x以下で割ることができるか
    // を調べれば良い H maxは答えの下限, これを高速に判断する必要がある O(n)以下
    // 全ての風船についてそれぞれ, (x - Hi) / Si = 風船を割るためのタイムリミット秒数, この秒数以内に風船を割らなくてはならない
    // つまり, タイムリミットが, それ以下の風船の数 - 1 が, タイムリミット以上になってはいけない, その風船自体は除く
    // これで, nlog(NS)*logNとなり多分間に合いそう
    // 0番目のやつはノーコストで割れる
    val HMAX = H.max
    def f(x: Long): Boolean = {
      val T = (0 until N).map(i => (x - H(i)) / S(i)).sorted.toArray
      // タイムリミット <= tの風船が何個あるか
      def binarySearch(t: Long): Int = {
        def recursive(l: Int, r: Int): Int =
          if (r - l > 1) {
            val mid = l + (r - l) / 2
            if (T(mid) > t) recursive(l, mid)
            else recursive(mid, r)
          } else l
        recursive(0, N)
      }
      (0 until N).forall(i => (binarySearch(T(i)) - 1) < T(i))
    }

    def binarySearch(lower: Long, upper: Long): Long = {
      def recursive(l: Long, r: Long): Long =
        if (r - l > 1) {
          val mid = l + (r - l) / 2
          if (HMAX <= mid && f(mid)) recursive(l, mid)
          else recursive(mid, r)
        } else r
      recursive(lower, upper)
    }

    val r = binarySearch(-1, (2 * Math.pow(10, 9) * Math.pow(10, 5)).toLong)
    println(r)
  }

}
