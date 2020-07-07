package jp.atcoder.sumitrust2019.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt(); sc.nextLine()
    val S = sc.nextLine().toList
    // 3桁の暗証番号, N桁のラッキーナンバーS, SからN - 3桁を消して, 残りの3桁を 左から読んだものを暗証番号として設定
    // 設定されうる暗証番号は何種類?
    // 残す桁を一つづつ選ぶ場合だと, N^3 なので, 間に合わない.
    // 最大で, 9 ^ 3種類しかない, というのはわかっている
    // 逆に, 9 ^ 3種類の中からSで満たすものがあるか？を調べた方が良さそう
    // 9 ^ 3通りの組み合わせで, Nについて走査すればいい
    def check(a: Int, b: Int, c: Int): Boolean = {
      def recursive(s: List[Char], rest: List[Char]): Boolean =
        rest match {
          case Nil => true
          case h :: t =>
            s match {
              case Nil => false
              case hh :: tt =>
                if (h == hh) recursive(tt, t)
                else recursive(tt, rest)
            }
        }
      recursive(S, List(a, b, c).map(i => (i + '0').toChar))
    }

    val r = (for {
      a <- 0 to 9
      b <- 0 to 9
      c <- 0 to 9
      if check(a, b, c)
    } yield (a, b, c)).size



    println(r)
  }

}
