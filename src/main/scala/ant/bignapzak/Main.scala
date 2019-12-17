package ant.bignapzak

import scala.annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val n = 4
    val w = 5
    val W = Array(2L, 1L, 3L, 2L)
    val V = Array(3L, 2L, 4L, 2L)

    def solve(n: Int, w: Int, W: Array[Long], V: Array[Long]): Long = {
      val N = n / 2

      def enumerate(from: Int, to: Int): Seq[(Long, Long)] = {
        @tailrec
        def loop(stack: List[(Int, (Long, Long))], sink: List[(Long, Long)]): Seq[(Long, Long)] =
          if (stack.nonEmpty) {
            val (i, (w2, v2)) = stack.head
            if (i < to) loop((i + 1 -> (w2, v2)) :: (i + 1 -> (w2 + W(i), v2 + V(i))) :: stack.tail, sink)
            else loop(stack.tail, (w2, v2) :: sink)
          } else sink
        loop((from -> (0L, 0L)) :: Nil, Nil)
      }

      def exclude(sorted: Seq[(Long, Long)]): IndexedSeq[(Long, Long)] = {
        @tailrec
        def loop(source: Seq[(Long, Long)], sink: Vector[(Long, Long)], max: Long): IndexedSeq[(Long, Long)] =
          if (source.nonEmpty) {
            val (w, v) = source.head
            if (w > max) loop(source.tail, sink :+ (w, v), v)
            else loop(source.tail, sink, max)
          } else sink
        loop(sorted, Vector.empty, 0)
      }

      val I = exclude(enumerate(N, n).sorted(Ordering.Tuple2(Ordering.Long, Ordering.Long.reverse)))
      val M = I.size

      def upperBounds(w: Long): Long = {
        @tailrec
        def loop(left: Int, right: Int): Long =
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (I(mid)._1 > w) loop(left, mid)
            else loop(mid, right)
          } else I(left)._2
        loop(-1, M)
      }

      enumerate(0, N).map(e => e._2 + upperBounds(w - e._1)).max
    }

    println(solve(n, w, W, V))
  }

}
