package ant.poj2456

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val X = Array.fill(N)(sc.nextInt()).sorted
    val X_MAX = Math.pow(10, 9).toInt

    // 最小値の最大化, 最大値の最小化
    // 最も近い二頭の牛の間隔を最大化
    // C(d) = 任意の牛の間隔を d 以上にできる
    // この判定は次のように貪欲法を用いて簡単に行える
    // 牛舎の位置 xをソートする
    // 最初の牛をx0に入れる
    // i番目の牛をxjに入れたら, i+1番目の牛はxj+d <=xkとなるような最小のxkを入れる
    // xのソートは最初に一回, 一回の判定では, 各牛舎についてたかだか一回しか
    // 調べない O(NlogN) + O(NlogN) = O(NlogN)

    def upperBound(left: Int, right: Int, f: Int => Boolean): Int = {
      def recursive(left: Int, right: Int): Int =
        if (right - left > 1) {
          val mid = left + (right - left) / 2
          if (f(mid)) recursive(mid, right)
          else recursive(left, mid)
        } else left
      recursive(left, right)
    }

    // 任意の牛の間隔をd以上にできるか?
    val f: Int => Boolean = d => {
      def loop(cows: Int, i: Int, last: Int): Boolean =
        if (cows > 0) { // 牛が残っていて
          if (i >= N) false // 既に牛舎を使い果たした場合, できない.
          else if (X(i) - X(last) >= d) loop(cows-1, i+1, i) // 最後に牛を置いた位置との差がd以上なら置く
          else loop(cows, i+1, last) // そうでないなら, 置かずに次の牛舎を見る
        } else true // 牛を全て置けたらtrue
      loop(M-1, 1, 0) // 初期状態で, 1匹 0番目に置いておく
    }

    val res = upperBound(0, X_MAX + 1, f)
    println(res)
  }

}
