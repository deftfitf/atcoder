package jp.microad.atcoder.abc117.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val K = sc.nextLong()
    val A = Array.fill(N)(sc.nextLong())

    // 各ビット桁毎に足し算は独立できる。
    // 各ビット桁毎に, 0が多ければ1を対応させ, 1が多ければ0を対応させる.
    val BIT_COUNT_1, BIT_COUNT_0 = new Array[Int](40)

    A.foreach { A =>
      val AB = A.toBinaryString.reverse.padTo(40, '0')
      var i = 0
      while (i < AB.length) {
        AB(i) match {
          case '1' => BIT_COUNT_1(i) += 1
          case '0' => BIT_COUNT_0(i) += 1
        }
        i += 1
      }
    }

    val ziped = BIT_COUNT_0.zip(BIT_COUNT_1)
    val maxL = BIT_COUNT_0.zip(BIT_COUNT_1).takeWhile(z => z._1 > 0 || z._2 > 0).length

    var i = 0
    var KBB = ""
    val KB = K.toBinaryString
    var MAX = 0L
    while (i < KB.length) {
      KB(i) match {
        case '1' =>
          val pre = java.lang.Long.parseLong(KBB.padTo(KB.length, '0'), 2)
          val aft = java.lang.Long.parseLong(ziped.take((KB.length - i - 1) min maxL). map { bit =>
            if (bit._1 > bit._2) '1'
            else '0'
          }. reverse. mkString. padTo(1, '0'), 2)
          val X = pre + aft
          MAX = MAX max A.foldLeft(0L)((b, n) => b + (n ^ X))
        case '0' => //
      }
      KBB += KB(i)
      i += 1
    }
    MAX = MAX max A.foldLeft(0L)((b, n) => b + (n ^ K))

    println(MAX)
  }

}
