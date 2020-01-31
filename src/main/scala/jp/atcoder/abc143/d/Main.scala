package jp.atcoder.abc143.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val N = sc.nextInt()
    val L = Array.fill(N)(sc.nextInt()).sorted(Ordering.Int.reverse)

    def binarySearch(a: Int, b: Int): Int = {
      def recursive(l: Int, r: Int): Int =
        if (r - l > 1) {
          val m = l + (r - l) / 2
          if (L(m) <= L(a) - L(b)) recursive(l, m)
          else recursive(m, r)
        } else l
      recursive(b, N)
    }

    var triangles = 0
    var a = 0
    while (a < N -2) {
      var b = a + 1
      while (b < N - 1) {
        val cMin = binarySearch(a, b)
        triangles += cMin - b
        b += 1
      }
      a += 1
    }

    println(triangles)
  }

}
