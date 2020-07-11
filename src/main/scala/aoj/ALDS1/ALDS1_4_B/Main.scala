package aoj.ALDS1.ALDS1_4_B

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val n = sc.nextInt()
    val S = Array.fill(n)(sc.nextInt()).sorted
    val q = sc.nextInt()
    val T = Array.fill(q)(sc.nextInt())

    // n個の整数を含む整列Sと、q個の異なる整数を含むTを読み込み
    // Tに含まれる整数の中でSに含まれるものの個数Cを出力するプログラムを作成
    def binarySearch(t: Int): Boolean = {
      def recursive(l: Int, r: Int): Int =
        if (r - l > 1) {
          val mid = l + (r - l) / 2
          if (S(mid) > t) recursive(l, mid)
          else recursive(mid, r)
        } else l
      val l = recursive(-1, n)
      if (l >= 0 && l < n) S(l) == t
      else false
    }
    val r = T.count(t => binarySearch(t))
    println(r)
  }

}
