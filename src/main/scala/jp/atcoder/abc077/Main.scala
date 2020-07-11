package jp.atcoder.abc077

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt()).sorted
    val B = Array.fill(N)(sc.nextInt())
    val C = Array.fill(N)(sc.nextInt()).sorted(Ordering.Int.reverse)

    // 3カテゴリーのパーツがそれぞれN個ずつあります
    // パーツのサイズ
    // 中部のパーツのサイズは、上部のパーツのサイズより真に大きく
    // 下部のパーツのサイズは、中部のパーツのサイズより真に大きくなければなりません
    // 作れる祭壇は何種類あるでしょうか/
    // ただし 2つの祭壇が異なるとは, 上部, 中部, 下部に使われるピースのうち少なくとも1つが異なる
    // 1つ決めた時の, そのパーツより大きいパーツはO(log N)で出すことができる
    // 普通にやると, O(N^2logN)とかになる, 間に合わない
    // なので, 中部を先に決めておく
    // 中部を決めた時, その時上部のパーツに使えるのは, 中部より小さいもの
    // 下部のパーツに使えるのは, 中部より大きいもの,
    // これらは それぞれ O(log N)で求めることができ, O(NlogN)で求めることができる

    def upperBounds(A: Array[Int], b: Int): Int = {
      def recursive(l: Int, r: Int): Int =
        if (r - l > 1) {
          val mid = l + (r - l) / 2
          if (A(mid) >= b) recursive(l, mid)
          else recursive(mid, r)
        } else l
      recursive(-1, N)
    }

    def lowerBounds(C: Array[Int], b: Int): Int = {
      def recursive(l: Int, r: Int): Int =
        if (r - l > 1) {
          val mid = l + (r - l) / 2
          if (C(mid) <= b) recursive(l, mid)
          else recursive(mid, r)
        } else l
      recursive(-1, N)
    }

    val r = (0 until N).foldLeft(0L) { case (sum, i) =>
      sum + (upperBounds(A, B(i)) + 1L) * (lowerBounds(C, B(i)) + 1L)
    }

    println(r)
  }

}
