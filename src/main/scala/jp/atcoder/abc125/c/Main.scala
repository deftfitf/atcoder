package jp.atcoder.abc125.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // 一つ選んで書き換えた際の
    // 最大公約数の最大値
    // ある数を選択した時, その数を書き換えて最大の公約数を作る場合
    // それ以外の数で作られる最大公約数のよりも大きな最大公約数を作ることが
    // できないので、それ以外の数で作られる最大公約数がその数を選択した
    // 場合における最大公約数となる

    // 選んだ場合と選ばなかった場合
    // 単に計算すると, N - 1個の整数について最大公約数を求め
    // それをN回繰り返すことになる。
    // のでO(N^2)以上にはなる.　これでは間に合わない
    // Ai を選択する時
    // A0 - Ai-1 の最大公約数
    // Ai+1 - AN の最大公約数
    // がわかっていれば, A0 - Ai-1 の最大公約数 と Ai+1 - ANの最大公約数の
    // 最大公約数を求めれば求めたい数になる
    def gcd(a: Long, b: Long): Long =
      if (a % b == 0) b
      else gcd(b, a % b)

    def solve(N: Int, A: Array[Int]): Long = {
      def calcLeftGCD(): Array[Long] = {
        val left = new Array[Long](N)
        def loop(i: Int, acm: Long): Unit =
          if (i < N - 1) {
            left(i) = gcd(acm, A(i).toLong)
            loop(i + 1, left(i))
          }
        left(0) = A(0)
        loop(1, A(0))
        left
      }
      def calcRightGCD(): Array[Long] = {
        val right = new Array[Long](N)
        def loop(i: Int, acm: Long): Unit =
          if (i > 0) {
            right(i) = gcd(acm, A(i).toLong)
            loop(i - 1, right(i))
          }
        right(N - 1) = A(N - 1)
        loop(N - 2, right(N - 1))
        right
      }
      val left = calcLeftGCD()
      val right = calcRightGCD()

      def loop(i: Int, max: Long): Long =
        if (i < N) {
          loop(i + 1, max max {
            if (i > 0 && i < N - 1) gcd(left(i - 1), right(i + 1))
            else if (i > 0) left(i - 1)
            else right(i + 1)
          })
        } else max
      loop(0, 0)
    }

    println(solve(N, A))
  }

}
