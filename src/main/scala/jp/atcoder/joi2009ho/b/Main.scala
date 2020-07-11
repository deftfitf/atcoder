package jp.atcoder.joi2009ho.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val d, n, m = sc.nextInt()
    val _D = new Array[Int](n)
    for (i <- (1 until n)) {
      _D(i) = sc.nextInt()
    }
    val D = _D.sorted
    val K = Array.fill(m)(sc.nextInt())

    // n <= 10^5, m <= 10^4
    // 一個ずつ注文を調べていく. つまり, 宅配先のupperboundを取ってその距離を取る
    def upperBounds(k: Int): Int = {
      def recursive(l: Int, r: Int): Int =
        if (r - l > 1) {
          val mid = l + (r - l) / 2
          if (D(mid) > k) recursive(l, mid)
          else recursive(mid, r)
        } else l
      recursive(0, n)
    }

    val r = K.foldLeft(0)((sum, k) => {
      val lower = upperBounds(k)
      val upper = (lower + 1)
      sum + ((k - D(lower)).abs min ((if (upper < n) D(upper) else d) - k).abs)
    })
    println(r)
  }

}
