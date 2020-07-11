package jp.atcoder.joi2008yo.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val R, C = sc.nextInt()
    val A = Array.fill(R, C)(sc.nextInt())

    // 横の裏返す行を選択するのと, 縦の裏返す行を選択するのを２パターン全探索する
    // ただし, 制約では, R <= 10, C <= 10000 となっている
    // 縦に関しては, 全探索可能だが, 横に対しては全探索できない
    // ここで, 縦に関して全探索する時, どの行を裏がえすかが決まっていれば,
    // どの列を裏がえすかを決めるかは, 裏返した時に出荷できる枚数が増えるか増えないかを判断すれば良い
    // O(2^R * RC) で計算できる.
    val r = (0 until Math.pow(2, R).toInt).foldLeft(0L)((max, s) =>
      max max (0 until C).foldLeft(0L)((sum, c) => {
        val count = (0 until R).count(r => {
          if ((s & (1 << r)) == (1 << r)) {
            A(r)(c) == 0
          } else {
            A(r)(c) == 1
          }
        })
        sum + (count max (R - count))
      })
    )

    println(r)
  }

}
