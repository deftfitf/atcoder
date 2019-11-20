package jp.microad.atcoder.abc144.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val N = sc.nextInt()
    val K = sc.nextLong()
    val A = Array.fill(N)(sc.nextLong()).sorted
    val F = Array.fill(N)(sc.nextLong()).sorted(Ordering.Long.reverse)
    val AF: Array[(Long, Long)] = A zip F

    val MAX = A.last * F.head

    def binarySearch(l: Long, r: Long): Long =
      if (r - l > 1) {
        val X = l + (r - l) / 2
        val _K = AF.foldLeft(0L) { case (r, (a, f)) =>
          r + ((a - X / f) max 0)
        }
        if (_K <= K) binarySearch(l, X)
        else binarySearch(X, r)
      } else r

    println(binarySearch(-1, MAX))
  }

}
