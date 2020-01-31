package jp.atcoder.jsc2019qual.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val M, D = sc.nextInt()

    var count = 0L
    var m = 1
    while (m <= M) {
      var d = 22
      while (d <= D) {
        val Array(d10, d1) = d.toString.map(_.toString.toInt).toArray
        if (d1 >= 2 && d1 * d10 == m) {
          count += 1
        }
        d += 1
      }
      m += 1
    }

    println(count)
  }

}
