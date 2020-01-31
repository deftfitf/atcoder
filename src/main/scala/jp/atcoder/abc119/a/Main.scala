package jp.atcoder.abc119.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val ymd = sc.nextLine().split("/").map(_.toInt)

    if (Ordering.Iterable[Int].lteq(ymd, Array(2019, 4, 30)))
      println("Heisei")
    else
      println("TBD")
  }

}
