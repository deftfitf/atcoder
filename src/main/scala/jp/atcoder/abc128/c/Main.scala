package jp.atcoder.abc128.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    val S = Array.fill(M)(0)
    for {
      i <- 0 until M
    } {
      val ki = sc.nextInt()

      for {
        _ <- 0 until ki
      } {
        val s = sc.nextInt()
        S(i) |= (1 << (s - 1))
      }
    }
    val P = Array.fill(M)(sc.nextInt())

    // 電球iのスイッチは, マスクを与えている.
    // (1 << sij)のfoldでマスクを形成し, ビットと&をとって, ビットを足し合わせて2で割った場合, piに等しければ点灯する
    // つまり, 全てのスイッチの状態を変化させ
    // 全ての電球について, その状態で, 全て条件を満たす場合につき, カウントする
    val r = (for {
      s <- 0 until Math.pow(2, N).toInt
      if (0 until M).forall { k =>
        (0 until N).foldLeft(0)((sum, b) => if (((s & (1 << b)) & S(k)) == (1 << b)) (sum + 1) % 2 else sum) == P(k)
      }
    } yield s).size

    println(r)
  }

}
