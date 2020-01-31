package jp.atcoder.abc075.c

object Main {

  class UnionFind(par: Array[Int]) {

    def root(x: Int): Int =
      if (par(x) == x) x
      else {
        par(x) = root(par(x))
        par(x)
      }

    def unite(x: Int, y: Int): Unit = {
      val rx = root(par(x))
      val ry = root(par(y))
      if (rx != ry)
        par(rx) = ry
    }

    def same(x: Int, y: Int): Boolean =
      root(par(x)) == root(par(y))

  }

  object UnionFind {
    def apply(N: Int): UnionFind =
      new UnionFind(Array.range(0, N))
  }

  def main(args: Array[String]): Unit = {
    // 頂点数が最大50頂点, 辺の数は最大50本.
    // 50本の辺について全て以下を試す
    // 対象の辺以外の全ての辺を繋いだDisjointSetを作り,
    // 対象の辺を繋ぐ前にすでに連結しているかを確認する.
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()

    case class Bridge(a: Int, b: Int)

    val bridges = new Array[Bridge](M)
    (0 until M) foreach { i =>
      val a, b = sc.nextInt()
      bridges(i) = Bridge(a-1,b-1)
    }

    var i = 0
    var count = 0
    while (i < M) {
      val set = UnionFind(N)
      var j = 0
      while (j < M) {
        if (i != j) {
          val bridge = bridges(j)
          set.unite(bridge.a, bridge.b)
        }
        j += 1
      }
      val bridge = bridges(i)
      if (!set.same(bridge.a, bridge.b))
        count += 1
      i += 1
    }

    println(count)
  }

}
