package jp.microad.atcoder.abc136.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val N, K = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // 0回以上K回以下, ある数を1増やして ある数を1減らすことができる
    // この時, Aの全ての要素を割り切る正の整数として考えられる値の最大値を計算
    // 方針を考える.
    // Aiを割り切れる数の最大値
    // 累積最小公倍数を求める
    // ただし, 0はスキップできる. どの数でも使える
    // 最初に0は必ず入ってこない

    // 累積していく時, 残り操作回数を使って,
  }

}
