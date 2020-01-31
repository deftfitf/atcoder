package jp.atcoder.abc115.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val P = Array.fill(N)(sc.nextInt()).sorted(Ordering.Int.reverse)

    println(P.head / 2 + P.tail.sum)
  }

}

