package jp.microad.atcoder.abc152.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val a, b = sc.nextInt()
    val aS = a.toString * b
    val bS = b.toString * a
    println(if (aS < bS) aS else bS)
  }

}
