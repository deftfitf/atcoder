package jp.microad.atcoder.abc119.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B, Q = sc.nextInt()
    val S = Array.fill(A)(sc.nextLong())
    val T = Array.fill(B)(sc.nextLong())
    val X = Array.fill(Q)(sc.nextLong()).zipWithIndex.sortBy(_._1)

    // 必ず, 一方向に進めばいいわけではない.
    // 行って戻ってくる方が近いケースもある.
    // 必ずsとtを一つ選ぶことになる.
    // xから見て, 選んだs,tが同じ方向にある場合には, xから遠い方の距離分移動すればいい
    // dist = max(|x - s|, |x - t|)
    // xから見て, 選んだs,tが異なる方向にある場合には, xから近い方の距離を往復して, 遠い方の距離分移動すればいいので
    // dist = 2 * min(|x - s|, |x - t|) + max(|x - s|, |x - t|)
    // なので, 実際, あるxについて候補があり
    // * xiから見て, 西側にペアがある場合
    //    xi から西にある si, ti の点について max(xi - si, xi - ti)
    // * xiから見て, 東側にペアがある場合
    //    xi から東にある si, ti の点について max(si - xi, ti - xi)
    // * xiから見て, 西にある si と 東にある ti を選択する場合
    //    2 * min(xi - si, ti - xi) + max(xi - si, ti - xi)
    // * xiから見て, 東にある si と 西にある ti を選択する場合
    //    2 * min(si - xi, xi - ti) + max(si - xi, xi - ti)
    // この4つの中で最初の値が最小の移動距離となる

    // つまり, xi から 1つ隣にある 寺院, 神社を高速に探す必要がある.
    // 全ての寺と, 全ての神社と, 全ての始点は全て異なる点に位置するので,
    // 開始点を小さい順にソートしておくと, 開始点の位置は, 寺院, 神社の間に属することになる
    // 開始点基準でインクリメントしていき, 10^5 回の繰り返しで十分にする

    val res = new Array[Long](Q)
    val Inf = Math.pow(10, 12).toLong

    var i = 0
    var sidx = 0
    var tidx = 0
    while (i < Q) {
      val (x, xidx) = X(i)

      while (sidx < A && S(sidx) < x) sidx += 1
      while (tidx < B && T(tidx) < x) tidx += 1

      val sldist = if (sidx - 1 >= 0) Math.abs(S(sidx - 1) - x) else Inf
      val srdist = if (sidx < A) Math.abs(S(sidx) - x) else Inf
      val tldist = if (tidx - 1 >= 0) Math.abs(T(tidx - 1) - x) else Inf
      val trdist = if (tidx < B) Math.abs(T(tidx) - x) else Inf

      res(xidx) =
        (sldist max tldist) min
          (srdist max trdist) min
            (2 * (sldist min trdist) + (sldist max trdist)) min
              (2 * (srdist min tldist) + (srdist max tldist))

      i += 1
    }

    println(res.mkString("\n"))
  }

}
