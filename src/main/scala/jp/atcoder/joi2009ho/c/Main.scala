package jp.atcoder.joi2009ho.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val P = Array.fill(N)(sc.nextInt()) :+ 0

    // 点数M以下の最大を求める
    // 獲得できる全ての点数を列挙した場合, 1000_C_4 + 1000_C_3 + 1000_C_2 + 1000_C_1 + 1000_C_0 通り. まあ間に合わない
    // とりあえず, 二つの点数の組までの組み合わせは列挙できそう
    // 2つの組み合わせを全探索して, さらに2つの組み合わせを, M以下の最大を選択すれば良さげ, 選択しない, という選択もあるので, 0点も含めておく
    val C = new Array[Int]((N + 1) * (N + 1))
    for {
      i <- 0 until (N + 1)
      j <- 0 until (N + 1)
    } C(i * (N + 1) + j) = P(i) + P(j)
    java.util.Arrays.sort(C)

    def binarySearch(x: Int): Int = {
      def recursive(l: Int, r: Int): Int =
        if (r - l > 1) {
          val mid = l + (r - l) / 2
          if (C(mid) > x) recursive(l, mid)
          else recursive(mid, r)
        } else l
      recursive(0, C.length)
    }

    val r = C.foldLeft(0)((m, c) => {
      val p = c + C(binarySearch(M - c))
      if (p <= M) m max p
      else m
    })
    println(r)
  }

}
