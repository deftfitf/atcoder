package jp.microad.atcoder.abc122.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val b = sc.next()(0)
    val r = b match {
      case 'A' => 'T'
      case 'T' => 'A'
      case 'C' => 'G'
      case 'G' => 'C'
    }
    println(r)
  }

}
