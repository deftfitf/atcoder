package jp.atcoder.past.past1.k

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val P = Array.fill(N)(sc.nextInt())
    val Q = sc.nextInt()
    val A, B = new Array[Int](Q)
    (0 until Q) foreach { i =>
      val a, b = sc.nextInt()
      A(i) = a
      B(i) = b
    }

    // aがbの部下かどうか? aの親を辿ってbに辿り着けば良い.
    // しかしツリーが平衡とは言っていないので,最悪計算量O(NQ).
    def query(a: Int, b: Int): Boolean = {
      def loop(current: Int): Boolean =
        if (current == b) true
        else if (current == -1) false
        else loop(P(current-1))
      loop(a)
    }
    // 普通にやると通らないので, 考え方を変える
    // 部下を全てキャッシュするのは悪手.
    // やはり木が平衡なら通過する気がするが,, 確実にQで10^5消費するので,
    // 全クエリでO(logN)ぐらいに収まる必要がある.
    //

    val r = (0 until Q)
      .map(i => query(A(i), B(i)))
      .map(r => if (r) "Yes" else "No").mkString("\n")

    println(r)
  }

}
