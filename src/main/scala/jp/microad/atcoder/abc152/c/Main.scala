package jp.microad.atcoder.abc152.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val P = List.fill(N)(sc.nextInt())

    def count(P: List[Int]): Int = {
      def loop(lst: List[Int], min: Int, acm: Int): Int =
        lst match {
          case Nil => acm
          case h :: t if h <= min => loop(t, h, acm + 1)
          case h :: t => loop(t, min, acm)
        }
      loop(P, N, 0)
    }

    println(count(P))
  }

}
