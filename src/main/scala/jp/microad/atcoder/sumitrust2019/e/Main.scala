package jp.microad.atcoder.sumitrust2019.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // 0 が来たら新しい色
    // カウンタは最大3つ, 最小1つあると考えられる
    // カウンタが交差する箇所では, 分岐が発生する

    // 人は必ずどれかの帽子をかぶる.
    // 常にi番目の人間の前には,
    // 色xを被っている人間がnxi人, 色yを被っている人間がnyi人, 色zを被っている人間がnzi人いる
    // つまりi番目の人間はAi人同じ色の帽子を被っている人がいるということは,
    // nxi, nyi, nziのうちAiと同じ数だけの通りがある
    // 最初は, nxi=0,nyi=0,nzi=0なので, A0 = 0で T0 = 3からスタートする

    // 常に3つの数を管理する. Ai - 1の数を1増やす
    def solve(N: Int, A: Array[Int]): Long = {
      val MAX = 1000000007
      val counts = new Array[Int](3)
      def loop(i: Int, acm: Long): Long =
        if (i < N) {
          val updated = (counts.count(_ == A(i)).toLong * acm) % MAX
          def update(j: Int): Unit =
            if (j < 3) {
              if (counts(j) == A(i)) counts(j) += 1
              else update(j + 1)
            }
          update(0)
          loop(i + 1, updated)
        } else acm
      loop(0, 1L)
    }

    println(solve(N, A))
  }

}
