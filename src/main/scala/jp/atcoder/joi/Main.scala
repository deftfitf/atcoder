package jp.atcoder.joi

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N, 2)(sc.nextInt())
    val AREA = A.map(a => (a(0), a(1))).toSet
    // 0 <= n <= 3000, 0 <= x, y <= 5000
    // 神殿の記述がある, 神殿は上から見ると正確に正方形になっており、その四隅には柱があった
    // 正方形になっているもののうち, 面積が最大のものが神殿に違いないと考えた
    // 柱の位置の座標が与えられるので、４本の柱でできる正方形のうち面積が最大のものを探し、その面積を出力するプログラムをかけ
    // なお, 正方形の辺は座標軸に平行とは限らないことに注意せよ

    // 3000本あるので単純に全ての組み合わせについて調べて, 正方形であること, その面積の大きさを調べようとする場合,
    // 全探索ではO(n^4)になり間に合わない
    // 制約から2点までは全探索することができそうだ
    // ここである2点を決めてしまうと, その2点の距離から正方形の一辺の長さが求まる
    // つまり, そこが一片とされる場合には, 他の点はそれぞれの辺から垂直に移動した場合の2ケース考えられる
    // その2ケースのうちどちらかが当てはまる場合には, 辺の長さ^2を有効な神殿の面積として計上する
    def exists(x: Int, dx: Int, y: Int, dy: Int): Boolean =
      x + dx >= 0 && x + dx <= 5000 &&
      y + dy >= 0 && y + dy <= 5000 &&
      AREA.contains((x + dx, y + dy))

    val m = (0 until (N - 1)).foldLeft(0L)((m, p1) =>
      ((p1 + 1) until N).foldLeft(m)((m, p2) => {
        val Array(x1, y1) = A(p1)
        val Array(x2, y2) = A(p2)
        val dx = x2 - x1
        val dy = y2 - y1
        if ((exists(x1, dy, y1, -dx) && exists(x2, dy, y2, -dx)) ||
            (exists(x1, -dy, y1, dx) && exists(x2, -dy, y2, dx)))
          (dx.toLong * dx.toLong + dy.toLong * dy.toLong) max m
        else m
      }))

    println(m)
  }

}
