package jp.atcoder.abc085.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, H = sc.nextInt()
    val A, B = new Array[Int](N)
    (0 until N) foreach { i =>
      val a, b = sc.nextInt()
      A(i) = a
      B(i) = b
    }

    // 常に ai <= bi, 投げた方が高い攻撃力
    // 振る場合, 常に 最大の ai で降った方が良さそう
    // この時最大の ai >= bi の剣は要らない
    // ai より 大きい bi の剣は何本かある
    // 振る順番を無視して考える
    // 最大のa * n 回 攻撃 する場合,
    // 投げた時めちゃくちゃ強い剣も存在するはず..
    // Biでソートしておいて, 途中から 最大のAiより小さくなった時にAiに切り替える
    // 既に使っている場合, 順番を入れ替える事でいける

    def solve(N: Int, H: Int, A: Array[Int], B: Array[Int]): Int = {
      val AMAX = A.max
      def loop(lst: List[Int], rest: Int, count: Int): Int =
        if (rest > 0) {
          lst match {
            case Nil => count + (rest / AMAX.toDouble).ceil.toInt
            case i :: t =>
              if (B(i) > AMAX) loop(t, rest - B(i), count + 1)
              else count + (rest / AMAX.toDouble).ceil.toInt
          }
        } else count
      loop((0 until N).sortBy(i => B(i))(Ordering.Int.reverse).toList, H, 0)
    }

    println(solve(N, H, A, B))
  }

}
