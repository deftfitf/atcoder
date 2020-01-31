package jp.atcoder.abc074.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B, C, D, E, F = sc.nextInt()
    // 砂糖の濃度を水 100g あたり Eg に近づけたい
    // 全体としてFg以下かつビーカーの中に砂糖と溶け残らせてはいけない
    // a + b <= F
    // b / (a + b) <= E / (100 + E)
    // 物質を抜く, という選択肢は無い
    // F <= 3000 なので, 全探索できそう <= 大嘘
    // 水の組み合わせだけ <= Fの範囲内で全列挙して
    // 入れれるだけのCを全列挙して
    // 残りのDは条件を見たすうち最も濃度が高くなるものを二分探索で探す
    // しかし, Cの時点でオーバーしている可能性があるのでそれを除去する

    def solve(): (Int, Int) = {
      val MAX = E.toDouble / (100F + E.toDouble)

      def upperBoundsD(water: Int, c: Int, upperD: Int): Int = {
        val base = (water + C * c).toDouble
        def loop(left: Int, right: Int): Int =
          if (right - left > 1) {
            val d = left + (right - left) / 2
            if (((C * c + D * d) / (base + D * d)) > MAX) loop(left, d)
            else loop(d, right)
          } else left
        loop(0, upperD + 1)
      }

      (for {
        a <- 0 to (F / (100 * A))
        b <- 0 to ((F - (100 * A * a)) / (100 * B))
        if !(a == 0 && b == 0)
        water = 100 * A * a + 100 * B * b
        maxC = (F - water) / C
        c <- 0 to maxC
        d = upperBoundsD(water, c, (F - water - C * c) / D)
        suger = C * c + D * d
        if (suger / (suger + water).toDouble) <= MAX
      } yield (suger + water, suger)).maxBy{ case (sugerWater, suger) => suger / sugerWater.toDouble }
    }

    val (a, b) = solve()
    println(s"$a $b")
  }

}
