package jp.atcoder.square869120_4.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // N個の建物が左から右へと一直線上に並んでいる
    // 左からi番目の建物は色iで塗られており,
    // 高さは現在aiです。
    // 左から見たときに、K色以上の色の建物が見えるという条件を満たして欲しいと思いました
    // 高さを増やすことができるが、減らすことはできない
    // 建物iが見えるとは, 建物iの前のjについて, iの高さ以上のものが存在しなこと
    // つまり, 連続した高さの建物の場合には, 左から見たときには１色にしか見えないということ
    // N個の建物があり, N < 15なので, 全ての建物について 高さを増やすか増やさないかを考えて
    // しかし, 増やすか増やさないかの判断はどうやるか
    // 手前から順番に増やすとして, それが最安になるかどうかは不明
    // N = Kの場合, 全ての場合に前のより多くすればいいが,
    // そうでないケースについては異なる
    // とりあえず, 見えるようにする建物を先に選ぶ
    // 選んだ後に, 今現在最も高い高さの建物のサイズを記録しておき, そのサイズ+1まで延ばす
    // 延ばしているときに, 今, 何色見えているかを記録しておき, K色以上見えないときは除去する

    val cost = (0 until Math.pow(2, N).toInt).foldLeft(Long.MaxValue)((min, s) => {
      val (colors, _, cost) = (0 until N).foldLeft((0, 0, 0L))((state, b) => {
        val (colors, maxHeight, cost) = state
        if ((s & (1 << b)) == (1 << b)) {
          if (A(b) <= maxHeight) (colors + 1, maxHeight + 1, cost + (maxHeight + 1) - A(b))
          else (colors + 1, A(b), cost)
        } else {
          if (A(b) <= maxHeight) (colors, maxHeight, cost)
          else (colors + 1, A(b), cost)
        }
      })
      if (colors < K) min
      else min min cost
    })

    println(cost)
  }

}
