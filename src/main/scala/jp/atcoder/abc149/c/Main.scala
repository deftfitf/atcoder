package jp.atcoder.abc149.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val X = sc.nextInt()

    def eratosthenes(n: Int): Seq[Int] = {
      val upper = Math.sqrt(n)
      def loop(lst: List[Int], used: List[Int]): List[Int] =
        if (lst.head < upper) {
          loop(lst.tail.filter(_ % lst.head != 0), lst.head :: used)
        } else used.reverse ++ lst
      loop((2 to n).toList, Nil)
    }
    val MAX = Math.pow(10, 6).toInt
    println(eratosthenes(MAX).find(_ >= X).get)
  }

}
