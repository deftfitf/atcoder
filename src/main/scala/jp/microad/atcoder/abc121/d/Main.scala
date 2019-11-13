package jp.microad.atcoder.abc121.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val _A, B = sc.nextLong()
    val A = _A - 1
    val R = new Array[Long](40)

    var i = 0
    while (i < 40) {
      val pow = Math.pow(2, i).toLong
      val a =
        ((A + 1) / pow / 2 * pow) + Math.max(((A + 1) % (2 * pow)) - pow, 0)
      val b =
        ((B + 1) / pow / 2 * pow) + Math.max(((B + 1) % (2 * pow)) - pow, 0)
      R(40 - i - 1) = (b - a) % 2L
      i += 1
    }
    println(java.lang.Long.parseLong(R.mkString(""), 2))
  }

}

