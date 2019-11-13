package lib.unionfind

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
