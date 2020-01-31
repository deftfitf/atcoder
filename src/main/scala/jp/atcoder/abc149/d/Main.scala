package jp.atcoder.abc149.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val R, S, P = sc.nextInt(); sc.nextLine()
    val T = sc.nextLine().toCharArray

    // じゃんけんバトル
    // プレイヤーは筐体とN回じゃんけんを行う
    // アイコの場合、１回のじゃんけんと数える
    // プレイヤがじゃんけんに買った場合、プライヤーはだそたてで以下のスコアをえる
    // ただし、K回前のじゃんけんで出した手と同じ手を出せない(K回目までのじゃんけんで好きな手を出せる
    // 先に出す手がわかっている
    // なので, 出した手を記録しておいて、K回先のやつでK回前の手を参照し、その次のやつで勝てる手に影響がないものを出す
    def solve(N: Int, K: Int, R: Int, S: Int, P: Int, T: Array[Char]): Long = {
      val used = new Array[Char](N)
      val set = Set('r', 's', 'p')
      def win(i: Int): (Char, Int) =
        T(i) match {
          case 'r' => ('p', P)
          case 's' => ('r', R)
          case 'p' => ('s', S)
        }
      def loop(i: Int, acm: Long): Long =
        if (i < N) {
          if (i >= K) {
            val (c, point) = win(i)
            if (c == used(i - K)) {
              if (i + K < N) {
                val (c2, _) = win(i + K)
                used(i) = (set - c2 - c).head
                loop(i + 1, acm)
              } else loop(i + 1, acm)
            } else {
              used(i) = c
              loop(i + 1, acm + point)
            }
          } else {
            val (c, point) = win(i)
            used(i) = c
            loop(i + 1, acm + point)
          }
        } else acm
      loop(0, 0)
    }

    println(solve(N, K, R, S, P, T))
  }

}
