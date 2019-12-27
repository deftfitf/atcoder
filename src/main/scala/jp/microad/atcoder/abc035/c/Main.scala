package jp.microad.atcoder.abc035.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, Q = sc.nextInt()
    val L, R = new Array[Int](Q)
    (0 until Q) foreach { i =>
      val l, r = sc.nextInt()
      L(i) = l
      R(i) = r
    }

    // あるオセロについて, 偶数回裏返されていれば黒, 奇数回裏返されていれば白になる
    // そこで, これを高速に求めたい.
    // ここで [l_i, r_i] をひっくり返す行為は, [0, r_i] をひっくり返してから, [0, l_i) をひっくり返すことと等しい
    // ので, [l_i, r_i] をひっくり返す Q回 の操作は実際には
    // [0, r_i], [0, l_i) をひっくり返すそれぞれ Q回 の操作と考えることができる
    // ひっくり返す順番は関係ないので, 全ての(r_i), (l_i - 1)をソートして,大きい順番にひっくり返していく
    // 一番大きい右端は1回ひっくり返すことになる
    // 以降, 新しいひっくり返しが発生する度に, その左に存在する区間はひっくり返しが発生した回数だけひっくり返されていることになる
    // よって O(2*Q) = O(N) で求まる
    def solve(N: Int, Q: Int, L: Array[Int], R: Array[Int]): Array[Int] = {
      val P = new Array[Int](N + 1)
      (0 until Q) foreach { i =>
        P(L(i) - 1) += 1
        P(R(i)) += 1
      }
      val result = new Array[Int](N)
      def loop(i: Int, acm: Int): Unit =
        if (i > 0) {
          result(i - 1) = (P(i) + acm) % 2
          loop(i - 1, P(i) + acm)
        }
      loop(N, 0)
      result
    }

    println(solve(N, Q, L, R).mkString)
  }

}
