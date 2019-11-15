package jp.microad.atcoder.abc144.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val a, b, x = sc.nextDouble()

    val h = x / (a * a)
    if (h >= b / 2) {
      println(Math.atan2(b - h, a / 2) * 180 / Math.PI)
    } else {
      println(Math.atan2(b, 2 * x / (a * b)) * 180 / Math.PI)
    }
  }

}
