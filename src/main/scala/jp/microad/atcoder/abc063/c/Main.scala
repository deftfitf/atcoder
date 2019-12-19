package jp.microad.atcoder.abc063.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val S = Array.fill(N)(sc.nextInt())
    val SUM = S.sum
    val NONZERO = S.filter(_ % 10 != 0)

    val r = if (SUM % 10 == 0) (if (NONZERO.length > 0) SUM - NONZERO.min else 0) else SUM
    println(r)
  }

}
