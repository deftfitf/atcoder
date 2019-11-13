package jp.microad.atcoder.abc132.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    // 最大 N - K個のしきりを作れる.
    // i 回の操作が必要になる時,
    // 「赤によるしきりが i - 1 個できる時の組み合わせの数」が答えである.
    // しきりを作れない場合には, 0となる
    // しきりを作れる場合には, まず仕切りが赤玉1つを配置して i - 1 個作られる

    // 残りの (N - K) - (i - 1) 個を 各しきりもしくは 先頭、末尾に配置する組み合わせになる
    // つまり, 残りの赤玉のN - K - iの隙間の間に i - 1個の
    // 残りの玉 を配置するのを 選択する
    // 隙間を選択しないという選択肢もあるので,
    // i + 1個の選択肢の中から, 分割する箇所を N - K - i 回選択するという事になる
    // (N - K - i)^(i + 1)

    val N, K = sc.nextInt()
    println((1 to K). map { i =>
      if ((i - 1) <= (N - K)) {
        Math.pow(N - K - i + 1, i + 1).toLong
      } else 0
    }. mkString("\n"))
  }

}
