package jp.microad.atcoder.abc135.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B = sc.nextLong()
    val K1 = (A + B) / 2
    val K2 = (- A - B) / 2

    val r = if (A == B) "0"
    else if ((A + B) % 2 == 0) {
      if (Math.abs(A - K1) == Math.abs(B - K1))
        K1.toString
      else if (Math.abs(A - K2) == Math.abs(B - K2))
        K2.toString
      else "IMPOSSIBLE"
    } else "IMPOSSIBLE"
    println(r)
  }

}
