package jp.atcoder.aoj.grl.grl2a

@annotation.Graph.Kruskal
@annotation.DataStructure.UnionFind
object MinimumSpanningTree {

  class UnionFind(par: Array[Int]) {

    def root(x: Int): Int =
      if (par(x) < 0) x
      else {
        par(x) = root(par(x))
        par(x)
      }

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

    def same(x: Int, y: Int): Boolean =
      root(x) == root(y)

    def size(x: Int): Int =
      -par(root(x))

  }

  object UnionFind {
    def apply(n: Int): UnionFind =
      new UnionFind(Array.fill(n)(-1))
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E = sc.nextInt()
    val S, T, W = new Array[Int](E)
    for {
      i <- 0 until E
    } {
      val s, t, w = sc.nextInt()
      S(i) = s
      T(i) = t
      W(i) = w
    }

    val disjointSet = UnionFind(V)
    val sumW = (0 until E).sortBy(i => W(i)).foldLeft(0L)((w, i) =>
      if (!disjointSet.same(S(i), T(i))) {
        disjointSet.union(S(i), T(i))
        w + W(i)
      } else w)

    println(sumW)
  }

}
