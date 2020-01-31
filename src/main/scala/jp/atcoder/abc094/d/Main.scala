package jp.atcoder.abc094.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt()).sorted

    // comb(ai, aj), これは ai を固定した時には,
    // 単峰関数のような関数 f_ai(x) = comb(ai, x)
    // となる。すると, 最大の値の候補は
    // ai の真ん中もしくは, 真ん中に近い2つの値が候補になる
    // これは, 二分探索で探せるのでいける
    // これを全てのaiについて調べても間に合う
    // 実際に combを計算する必要はない
    // ... 全ての ai について調べると, どうやらそうでないケースがある
    // 最大の ai について調べるだけでいい
    def solve(N: Int, A: Array[Int]): (Int, Int) = {
      def upperBounds(n: Int, upper: Int): Int = {
        @scala.annotation.tailrec
        def loop(left: Int, right: Int): Int =
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (A(mid) >= n) loop(left, mid)
            else loop(mid, right)
          } else right
        loop(-1, upper)
      }
      def lowerBounds(n: Int, upper: Int): Int = {
        @scala.annotation.tailrec
        def loop(left: Int, right: Int): Int =
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (A(mid) <= n) loop(mid, right)
            else loop(left, mid)
          } else left
        loop(-1, upper)
      }
      @scala.annotation.tailrec
      def loop(i: Int, diff: Int, n: Int, r: Int): (Int, Int) =
        if (i < N) {
          val center = (A(i) / 2.toDouble).round.toInt
          val li = lowerBounds(center, i)
          val ri = upperBounds(center, i)
          val left = if (li >= 0) A(li) else 0
          val right = if (ri < A(i)) A(ri) else A(i)
          if (left > A(i) - right) {
            if (left > diff) loop(i + 1, left, A(i), left)
            else loop(i + 1, diff, n, r)
          } else {
            if (A(i) - right > diff) loop(i + 1, A(i) - right, A(i), right)
            else loop(i + 1, diff, n, r)
          }
        } else (n, r)

      loop(N - 1, 0, A.last, 0)
    }

    val (n, r) = solve(N, A)
    println(s"$n $r")
  }

}

