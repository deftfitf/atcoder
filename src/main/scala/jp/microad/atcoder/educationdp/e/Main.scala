package jp.microad.atcoder.educationdp.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    // D問題と問題文は全く同一で、制約だけ変わりました
    // dp(i)(sum_w) := i-1番目までの品物がsum_wを超えないように選んだときの
    // 価値の総和の最大値としてしまうと, テーブルサイズがO(NW)隣、今回はW <= 10^9
    // なので、大変なことになってしまいます。
    // そこで発想を転換してあげて、
    // dp(i)(sum_v) := i-1番目までの品物から価値がsum_v以上を達成するように選んだ時の
    // 重さの総和の最小値
    // としてあげます
    // すると100 * 10^3 = 10^5
    // 最終的には、dp(N)(sum_v)の値が, W以下であるようなsum_vの値の最大値
    // を求めてあげればいいです。

    val N, W = sc.nextInt()
    val w, v = new Array[Int](N)
    (0 until N) foreach { i =>
      val _w, _v = sc.nextInt()
      w(i) = _w
      v(i) = _v
    }
    val VMAX = Math.pow(10, 5).toInt
    val Inf = 1000000001

    val dp = Array.fill(N+1, VMAX+1)(Inf)
    dp(0)(0) = 0
    var i = 0
    while (i < N) {
      var j = 0
      while (j <= VMAX) {
        if (j >= v(i))
          dp(i+1)(j) = (dp(i)(j - v(i)) + w(i)) min dp(i+1)(j) min dp(i)(j)
        else
          dp(i+1)(j) = dp(i+1)(j) min dp(i)(j)
        j += 1
      }
      i += 1
    }

    println(dp(N).lastIndexWhere(_ <= W))
  }

}
