package jp.atcoder.abc146.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B, X = sc.nextLong()

    def binarySearch(l: Long, r: Long): Long =
      if (r - l > 1) {
        val N = l + (r - l) / 2
        if ((A * N + B * N.toString.length) > X) binarySearch(l, N)
        else binarySearch(N, r)
      } else l

    println(binarySearch(0, Math.pow(10, 9).toLong + 1))
  }

}
