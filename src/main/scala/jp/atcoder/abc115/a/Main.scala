package jp.atcoder.abc115.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val D = sc.nextInt()
    val r = D match {
      case 25 => "Christmas"
      case 24 => "Christmas Eve"
      case 23 => "Christmas Eve Eve"
      case 22 => "Christmas Eve Eve Eve"
    }
    println(r)
  }

}

