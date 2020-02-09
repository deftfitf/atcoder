package jp.atcoder.abc154.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val Array(s, t) = sc.nextLine().split(" ")
    val A, B = sc.nextInt(); sc.nextLine()
    val u = sc.nextLine()

    if (u == s) println(s"${A-1} ${B}")
    else println(s"$A ${B-1}")
  }

}
