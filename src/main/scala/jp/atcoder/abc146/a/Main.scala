package jp.atcoder.abc146.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine()
    val week = Seq("SUN","MON","TUE","WED","THU","FRI","SAT").zipWithIndex.toMap
    println(7 - week(S))
  }

}