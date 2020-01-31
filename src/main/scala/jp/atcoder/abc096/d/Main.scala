package jp.atcoder.abc096.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()

    // 素数を N個 選んで, この選んだ素数のうち, 5個を選ぶと
    // どれでもその合計が合計数になるようなもの

    // まず, 候補となる素数を調べるのに √55555 回実行すればよく, 素数自体の全列挙は容易
    // 全探索系だと非現実的っぽいので, 法則を探す

    // 回答を見ると, 5n + 1 で表せる数かつ素数を列挙する.
    // 対象の素数を全列挙, 5n + 1 で表せる数 を N 個取り出す

    def eratosthenes(n: Int): Seq[Int] = {
      val upper = Math.sqrt(n)
      def loop(lst: List[Int], used: List[Int]): List[Int] =
        if (lst.head < upper) {
          loop(lst.tail.filter(_ % lst.head != 0), lst.head :: used)
        } else used.reverse ++ lst
      loop((2 to n).toList, Nil)
    }

    def solve(N: Int): List[Int] = {
      eratosthenes(55555).filter(_ % 5 == 1).take(N).toList
    }

    println(solve(N).mkString(" "))
  }

}
