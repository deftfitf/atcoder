package jp.microad.atcoder.abc133.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val L, R = sc.nextLong()

    // L <= i < j <= R
    // 2019で割り切れる数値が, L以上R以下で1つ以上ある時には, 当然最小値0
    // L以上R以下で1つも無い時には, Lの値がもっとも2019で割り切れる数が大きいということ
    // つまりL, L + 1を選べばいい
    // R - Lの中が2019個あれば問題ない
    val min = if (R - L >= 2019L) 0L
    else {
      val candidates = for {
        i <- L to (R - 1)
        j <- (i + 1) to R
      } yield (i * j) % 2019L
      candidates.min
    }
    println(min)
  }

}
