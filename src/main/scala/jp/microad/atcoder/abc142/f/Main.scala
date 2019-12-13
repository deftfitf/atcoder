package jp.microad.atcoder.abc142.f

import scala.collection.mutable

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val V, E = sc.nextInt()
    val G = Array.fill(V + 1)(List[Int]())
    (0 until E) foreach { _ =>
      val u, v = sc.nextInt()
      G(u) ::= v
    }

    def solve(V: Int, E: Int, G: Array[List[Int]]): Option[IndexedSeq[Int]] = {
      val visited = new Array[Boolean](V+1)
      val cycle = new Array[Boolean](V+1)

      def findCyclic(stack: List[Int]): Boolean =
        if (stack.nonEmpty) {
          val u = stack.head
          if (u > 0) {
            if (cycle(u)) {
              val copiedCycle = cycle.clone()
              if (check(takeEdges(u, u, stack.tail, mutable.Set.empty, copiedCycle), copiedCycle)) {
                takeEdges(u, u, stack.tail, mutable.Set.empty, cycle)
                true
              } else findCyclic(stack.tail)
            } else {
              cycle(u) = true
              visited(u) = true
              findCyclic(G(u).foldRight(-u :: stack.tail)((v, stack) => v :: stack))
            }
          } else {
            cycle(-u) = false
            findCyclic(stack.tail)
          }
        } else false

      def check(edges: Set[(Int, Int)], cycle: Array[Boolean]): Boolean =
        ((1 to V) filter cycle) flatMap (u => G(u).map((u, _))) forall { edge =>
          edges.contains(edge) || !cycle(edge._2)
        }

      def takeEdges(before: Int, start: Int, stack: List[Int], edges: mutable.Set[(Int, Int)], cycle: Array[Boolean]): Set[(Int, Int)] =
        if (stack.nonEmpty) {
          val u = stack.head
          if (-u == start) {
            removeOthers(stack.tail, cycle)
            edges.add((-u, before))
            edges.toSet
          } else {
            if (u < 0) {
              edges.add((-u, before))
              takeEdges(-u, start, stack.tail, edges, cycle)
            } else
              takeEdges(before, start, stack.tail, edges, cycle)
          }
        } else edges.toSet // unreachable.

      def removeOthers(stack: List[Int], cycle: Array[Boolean]): Unit =
        if (stack.nonEmpty) {
          val u = stack.head
          if (u < 0) cycle(-u) = false
          removeOthers(stack.tail, cycle)
        }

      def loop(s: Int): Option[IndexedSeq[Int]] =
        if (s <= V) {
          if (!visited(s) && findCyclic(s :: Nil)) Some((1 to V) filter cycle)
          else loop(s + 1)
        } else None

      loop(1)
    }

    solve(V, E, G) match {
      case Some(r) => println(r.size); println(r.mkString("\n"))
      case None => println(-1)
    }
  }

}
