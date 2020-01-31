package jp.atcoder.abc138.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val a = sc.nextInt(); sc.nextLine()
    val s = sc.nextLine()
    if (a >= 3200) println(s) else println("red")
  }

}
