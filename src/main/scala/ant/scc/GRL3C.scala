package ant.scc

import scala.collection.mutable.ArrayBuffer

object GRL3C {

  trait DisjointSet {
    def same(x: Int, y: Int): Boolean
  }

  class UnionFind(par: Array[Int]) extends DisjointSet {

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
          par(rx) += par(ry)
          par(ry) = rx
        } else {
          par(ry) += par(rx)
          par(rx) = ry
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

  def stronglyConnectedComponents(V: Int, G: Array[ArrayBuffer[Int]], inverseG: Array[ArrayBuffer[Int]]): DisjointSet =
    constructDisjointSet(V, topologicalSortDesc(V, G), inverseG)

  def topologicalSortDesc(V: Int, G: Array[ArrayBuffer[Int]]): List[Int] = {
    val visit = new Array[Boolean](V)
    @scala.annotation.tailrec
    def dfs(stack: List[Int], sorted: List[Int]): List[Int] =
      if (stack.nonEmpty) {
        val u = stack.head
        if (u < 0) {
          dfs(stack.tail, (-u - 1) :: sorted)
        } else if (!visit(u)) {
          visit(u) = true
          dfs(
            G(u).foldLeft(-(u + 1) :: stack.tail)(
              (stack, v) => v :: stack), sorted)
        } else dfs(stack.tail, sorted)
      } else sorted

    (0 until V).foldLeft(Nil: List[Int])((sorted, s) =>
      if (!visit(s)) dfs(s :: Nil, sorted)
      else sorted)
  }

  def constructDisjointSet(V: Int, sortedVertexes: List[Int], G: Array[ArrayBuffer[Int]]): DisjointSet = {
    val visit = new Array[Boolean](V)
    val disjointSet = UnionFind(V)
    @scala.annotation.tailrec
    def dfs(stack: List[Int], components: List[Int]): List[Int] =
      if (stack.nonEmpty) {
        val u = stack.head
        if (!visit(u)) {
          visit(u) = true
          dfs(
            G(u).foldLeft(stack.tail)((stack, v) => v :: stack),
            u :: components)
        } else dfs(stack.tail, components)
      } else components

    for {
      s <- sortedVertexes
    } if (!visit(s))
      dfs(s :: Nil, Nil)
        .sliding(2)
        .filter(_.size >= 2)
        .foreach(pair => disjointSet.union(pair.head, pair(1)))

    disjointSet
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val V, E = sc.nextInt()

    val G = Array.fill(V)(new ArrayBuffer[Int]())
    val inverseG = Array.fill(V)(new ArrayBuffer[Int]())
    for {
      _ <- 0 until E
    } {
      val s, t = sc.nextInt()
      G(s).append(t)
      inverseG(t).append(s)
    }

    val disjointSet =
      stronglyConnectedComponents(V, G, inverseG)

    val Q = sc.nextInt()
    val r = (0 until Q).foldLeft(new StringBuilder)((bldr, _) => {
      val u, v = sc.nextInt()
      if (disjointSet.same(u, v)) {
        bldr.append("1").append("\n")
      } else {
        bldr.append("0").append("\n")
      }
    }).result()

    print(r)
  }

}
