package jp.microad.atcoder.abc132.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    // 最短路が3Nで表せる場合. これは必ず到達できる
    // 巡回グラフの場合, 巡回道を通ると, 必ず最短路にはならないが,
    // 最短路が3Nで表せない場合, 巡回道を通った時にだけ辿りつける可能性がある.

    // 目標の頂点Tから出る全ての巡回道の距離を調べる。
    // 頂点Tに到達した時点で以下の2パターンがちょうど到達できていない.
    // 3N + 1
    // 3N + 2

    // 3N + 1の場合, 巡回道のコストが
    //   3Nしかなければ, 永遠に3N + 1を周り続ける
    //   3N + 1を含む場合には, 2週すればいい
    //   3N + 2を含む場合には, 1週すればいい
    // 3N + 2の場合, 巡回道のコストが
    //   3Nしかなければ, 永遠に3N + 1を周り続ける
    //   3N + 1を含む場合には, 1週すればいい
    //   3N + 2を含む場合には, 2週すればいい

    // 巡回の場合, 少なくとも 2 回巡回すればいい

    // 深さ優先探索をして, 巡回を記録, 2回巡回した場合には, それ以降, 進まない
    val N, M = sc.nextInt()
    val G = Array.fill[Boolean](N, M)(false)
    (0 until M) foreach { i =>
      val u, v = sc.nextInt()
      G(u)(v) = true
    }
    val S, T = sc.nextInt()

  }

}
