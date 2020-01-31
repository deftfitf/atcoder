package jp.atcoder.abc148.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt(); sc.nextLine()
    val _S = sc.nextLine().split(" ")
    val S = _S(0)
    val T = _S(1)
    val r = S.zip(T).map(z => z._1.toString ++ z._2.toString).reduce(_ ++ _)
    println(r)
  }

}
