package jp.atcoder.past.past1.a

import scala.util.{Success, Try}

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine()

    Try(S.toInt) match {
      case Success(value) => println(value * 2)
      case _ => println("error")
    }
  }

}
