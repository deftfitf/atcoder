package jp.atcoder.abc150.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt();
    val P, Q = Array.fill(N)(sc.nextInt())

    val ordering = Ordering.Iterable(Ordering.Int)
    val permutations =
      (1 to N).permutations.toIndexedSeq.sorted(ordering)
    def binarySearch(P: IndexedSeq[Int]): Int = {
      def loop(left: Int, right: Int): Int =
        if (right - left > 1) {
          val mid = left + (right - left) / 2
          if (ordering.gteq(permutations.apply(mid), P))
            loop(left, mid)
          else loop(mid, right)
        } else right
      loop(-1, permutations.size)
    }

    println((binarySearch(P) - binarySearch(Q)).abs)
  }

}
