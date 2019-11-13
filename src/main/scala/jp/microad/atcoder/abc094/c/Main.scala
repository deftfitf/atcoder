package jp.microad.atcoder.abc094.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val X = Array.fill(N)(sc.nextInt())
    val SX = X.zipWithIndex.sortBy(_._1)
    val itoi = SX.zipWithIndex.map(e => (e._1._2, e._2)).toMap
    val medianL = SX(N / 2 - 1)._1
    val medianR = SX(N / 2)._1

    val r = new Array[Int](N)
    var i = 0
    while (i < N) {
      if (itoi(i) < N / 2)
        r(i) = medianR
      else
        r(i) = medianL
      i += 1
    }
    println(r.mkString(" "))
  }

}
