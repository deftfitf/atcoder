package jp.atcoder.abc002.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val R = Array.fill(N+1, N+1)(false)
    for (_ <- 0 until M) {
      val x, y = sc.nextInt()
      R(x)(y) = true
      R(y)(x) = true
    }

    // 国会議員の数 N <= 12, 人間関係の数 M <= N^2
    // 制約からも, 適当な国会議員の組み合わせを抽出して, その国会議員が全て知り合いかどうか?を判定すれば良さそう
    // なので, 国会議員が知り合いかどうかを高速に判定するため, N x Nの隣接行列を構築する
    val r = (0 until Math.pow(2, N).toInt).foldLeft(0)((max, s) => {
      val candidates = (0 until N).filter(b => (s & (1 << b)) == (1 << b)).map(_ + 1)
      if (candidates.forall(x => candidates.forall(y => x == y || R(x)(y)))) max max candidates.size
      else max
    })

    println(r)
  }

}
