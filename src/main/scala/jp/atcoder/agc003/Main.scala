package jp.atcoder.agc003

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextLong())
    // 1からNまでの整数のうちどれかが書かれたカードをたくさん持っている
    // 整数iが書かれたカードをA_i枚持っている
    // 2枚のカードについて、それらに書かれた整数の差の絶対値が1以下の時、
    // これらをペアにすることができる. => つまり, iをペアにできるのはi-1,i+1のみ
    // 同じカードが複数のペアに使われないように、できるだけ多くのペアを作りたい
    // 作れるペアの個数の最大値
    // 同じ数のカードだけで完結する場合, 完結させた方がいいとは限らない
    // 最初から偶数枚用意しているカードを使いきるのもNG
    // i - 1のカードが奇数枚, iのカードが偶数枚, i + 1のカードが奇数枚の時
    // (i - 1, i), (i, i + 1)のペアを作ることで最大化できるため
    // なので, 基本的には, カードが奇数枚あるか, 偶数枚あるか, だけを考える必要がある
    // こういうことができるかは, 順番に繋がっているか繋がっていないか, だけで, どこからペアを作るかの順番は関係ない
    // 0番目の数から順番に, 奇数が出たら, 持ち越しする, 連番のカードが続く限り, 持ち越し続けても問題ない
    // 連番のカードが続くうちで, 奇数のカードが出たら相殺できる, 連番が止まったタイミングで持ち越し不可能になるので
    // カードはロストする. ロストしたカードの数を数えておく. 全てのカードの合計から, ロストしたカードの数を引いて2で割った数が解答となる
    def solve(N: Int, A: Array[Long]): Long = {
      @scala.annotation.tailrec
      def recursive(i: Int, lost: Int): Int = {
        if (i >= N) lost
        else if (A(i) % 2 == 1) recursiveFindingOddCard(i + 1, lost)
        else recursive(i + 1, lost)
      }
      @scala.annotation.tailrec
      def recursiveFindingOddCard(i: Int, lost: Int): Int = {
        if (i >= N) lost
        else if (A(i) == 0) recursive(i + 1, lost + 1)
        else if (A(i) % 2 == 1) recursive(i + 1, lost)
        else recursiveFindingOddCard(i + 1, lost)
      }
      (A.sum - recursive(0, 0)) / 2
    }

    println(solve(N, A))
  }

}
