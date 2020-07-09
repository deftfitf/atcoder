package jp.atcoder.joi2008yo.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val m = sc.nextInt()
    val P1 = Array.fill(m, 2)(sc.nextInt())
    val n = sc.nextInt()
    val P2 = Array.fill(n, 2)(sc.nextInt())
    val P = P2.map(arr => (arr(0), arr(1))).toSet

    // 星座を構成するm個の星の位置は全て異なる. また、写真に写っているn個の星の位置は全て異なる
    // 探したい星座の座標をどれだけ平行移動すれば写真の中の座標になるかを表す
    // 最初の整数がx方向に平行移動する量, 次の整数がy方向に平行移動する量である
    // 1 <= m <= 200, 1 <= n <= 1000
    // 単に全ての座標でのケースを調べようとすると,間に合わないので
    // 座標を当てはめていくイメージ
    // 適当な探したい星座の星を一つ決めて, その星を起点に探していくことにする
    // すると, 写真に写っている全ての星について, 起点の星が当てはまるかどうかを探すことになる
    // 当てはめた後に, m個の星について, 全て存在しているかどうかを確かめる
    // これで O(nm) で調べれそう
    // 起点の星と, 当てはめる星の一の差が平行移動量で, 他の星と一致しているかを確認するのにもこの値を当てはめる
    val Array(px, py) = P1(0)
    val (dx, dy) = (for {
      Array(tx, ty) <- P2
      dx = tx - px
      dy = ty - py
      if {
        P1.forall { case Array(px, py) =>
          P.contains((px + dx, py + dy))
        }
      }
    } yield (dx, dy)).head

    println(s"$dx $dy")
  }

}
