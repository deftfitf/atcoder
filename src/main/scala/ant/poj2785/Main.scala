package ant.poj2785

object Main {

  def main(args: Array[String]): Unit = {
    val _ = new java.util.Scanner(System.in)

    val N = 6
    val A = Array(-45, -41, -36, -36, 26, -32)
    val B = Array(22, -27, 53, 30, -38, -54)
    val C = Array(42, 56, -37, -75, -10, -6)
    val D = Array(-16, 30, 77, -46, 62, 45)

    val cd = (for {
      c <- C
      d <- D
    } yield c + d).sorted

    var count = 0
    for {
      a <- A
      b <- B
    } {
      count += upperBound(cd, -(a + b)) - lowerBound(cd, -(a + b))
    }

    def upperBound(arr: Array[Int], upper: Int): Int = {
      def loop(left: Int, right: Int): Int =
        if (right - left > 1) {
          val mid = left + (right - left) / 2
          if (arr(mid) > upper) loop(left, mid)
          else loop(mid, right)
        } else right
      loop(-1, arr.length)
    }

    def lowerBound(arr: Array[Int], lower: Int): Int = {
      def loop(left: Int, right: Int): Int =
        if (right - left > 1) {
          val mid = left + (right - left) / 2
          if (arr(mid) >= lower) loop(left, mid)
          else loop(mid, right)
        } else right
      loop(-1, arr.length)
    }

    println(count)
  }

}
