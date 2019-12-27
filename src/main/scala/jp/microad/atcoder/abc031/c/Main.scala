package jp.microad.atcoder.abc031.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // 高橋君が1つ丸をつけた時,
    // 青木君はそれに対して自分自身がもっとも多く得点を得られる要素に丸をつける
    // この選択はあくまでも最適ではない(勝てる選択とは限らない)
    // これをN通り試しそのうちの最大得点が答えだと考えられる

    // 負の数があるので, 単純に左端, 右端であれば良いものではない
    def solve(N: Int, A: Array[Int]): Int = {
      def calc(left: Int, right: Int): (Int, Int) =
        (left to right).foldLeft((0, 0)) { case ((tp, ap), i) =>
          if ((i - left + 1) % 2 == 1) (tp + A(i), ap)
          else (tp, ap + A(i))
        }
      (for {
        i <- 0 until N
      } yield {
        val candidate = for {
          j <- 0 until N
          if i != j
        } yield calc(i min j, i max j)
        candidate.find(_._2 == candidate.maxBy(_._2)._2).get._1
      }). max
    }

    println(solve(N, A))
  }

}
