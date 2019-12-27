package jp.microad.atcoder.arc062.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine().toCharArray

    // 各プレイヤーは, パーをグーよりも多く出してはいけない
    // 片方の手が全て先にわかっている状態で特点を最大化する
    // つまり, 入力として入ってくる手はそれを満たしている
    // 制約の中でより多く勝てばいい
    // グーかパーしか出せない
    // グーはいつでも出せる
    // 負けとあいこでは, あいこの方がいい
    // あいこでグーの数を稼がないと, パーは出すことができない
    // 途中どこかで負ける方が高いケースがあるか？ ..不明
    // とりあえず貪欲に行く
    // 勝ちに行った場所を負けた場所に補填したとして, 結局 +- 0なので変わらなそう
    def solve(S: Array[Char]): Int = {
      @scala.annotation.tailrec
      def loop(i: Int, g: Int, p: Int, point: Int): Int =
        if (i < S.length) {
          S(i) match {
            case 'g' =>
              if (p < g) loop(i + 1, g, p + 1, point + 1)
              else loop(i + 1, g + 1, p, point)
            case 'p' =>
              if (p < g) loop(i + 1, g, p + 1, point)
              else loop(i + 1, g + 1, p, point - 1)
          }
        } else point
      loop(0, 0, 0, 0)
    }

    println(solve(S))
  }

}
