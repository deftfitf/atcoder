package jp.microad.atcoder.abc094.d

// TODO: まだ
object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // comb(ai, aj), これは ai を固定した時には,
    // 単峰関数のような関数 f_ai(x) = comb(ai, x)
    // となる。すると, 最大の値の候補は
    // ai の真ん中もしくは, 真ん中に近い2つの値が候補になる
    // これは, 二分探索で探せるのでいける
    // これを全てのaiについて調べても間に合う
    // 実際に combを計算する必要はない
    def solve(N: Int, A: Array[Int]): (Int, Int) = {
      // aj > n となる 最小の aj を返す
      def upperBounds(n: Int): Int = {
        @scala.annotation.tailrec
        def loop(left: Int, right: Int): Int =
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (A(mid) > n) loop(left, mid)
            else loop(mid, right)
          } else right
        loop(-1, N)
      }
      // aj <= n となる最大の aj を 返す
      def lowerBounds(n: Int): Int = {
        @scala.annotation.tailrec
        def loop(left: Int, right: Int): Int =
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (A(mid) <= n) loop(left, mid)
            else loop(mid, right)
          } else left
        loop(0, N)
      }

      @scala.annotation.tailrec
      def loop(i: Int, diff: Int, n: Int, r: Int): (Int, Int) =
        if (i < N) {
          val center = A(i) / 2
          val left = A(lowerBounds(center))
          val right = A(upperBounds(center))
          if (center - left > right - center) {
            if (center - left > diff) loop(i + 1, center - left, A(i), left)
            else loop(i + 1, diff, n, r)
          } else {
            if (right - center > diff) loop(i + 1, right - center, A(i), right)
            else loop(i + 1, diff, n, r)
          }
        } else (n, r)

      loop(0, 0, 0, 0)
    }

    val (n, r) = solve(N, A)
    println(s"$n $r")
  }

}
