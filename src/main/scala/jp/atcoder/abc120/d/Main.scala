package jp.atcoder.abc120.d

object Main {

  class UnionFind(par: Array[Int]) {

    def union(x: Int, y: Int): Boolean = {
      val rx = root(x)
      val ry = root(y)
      if (rx != ry) {
        if (size(rx) < size(ry)) {
          par(ry) += par(rx)
          par(rx) = ry
        } else {
          par(rx) += par(ry)
          par(ry) = rx
        }
        true
      } else false
    }

    def root(x: Int): Int =
      if (par(x) < 0) x
      else {
        par(x) = root(par(x))
        par(x)
      }

    def size(x: Int): Int =
      -par(root(x))

  }

  object UnionFind {

    def apply(n: Int): UnionFind =
      new UnionFind(Array.fill(n)(-1))

  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    // 辺をつなぐ処理は簡単、しかし切り離す行為は簡単ではない

    case class Bridge(from: Int, to: Int)

    val Bridges = new Array[Bridge](M)
    (0 until M) foreach { i =>
      val a, b = sc.nextInt()
      Bridges(i) = Bridge(a-1, b-1)
    }

    // 全ての橋が壊れている状態
    // この時の不便さは？
    // 連結!
    // 繋いだ時, 始点から繋がっている数 * 終点から繋がっている数
    // BとCがもともと繋がっていた場合には（同じグループの場合には不便さは変わらない
    // BとCがもともと繋がっていない場合には、快適さが上がる。
    // この橋が落ちた時には, 片方のグループの島と, 片方のグループの島の組み合わせが断絶されることになるので
    // この組み合わせの数だけ不便になることになる。
    // つまり, 始点から繋がっている数 * 終点から繋がっている数
    val unionFind = UnionFind.apply(N)
    val res = new Array[Long](M)
    res(M-1) = N.toLong*(N.toLong-1L)/2L

    var i = M - 1
    while (i > 0) {
      val Bridge(a, b) = Bridges(i)
      if (unionFind.root(a) != unionFind.root(b)) {
        res(i-1) = res(i) - unionFind.size(a).toLong * unionFind.size(b).toLong
        unionFind.union(a, b)
      } else {
        res(i-1) = res(i)
      }
      i -= 1
    }

    println(res.mkString("\n"))
  }

}
