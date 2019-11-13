package jp.microad.atcoder.abc132.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = scala.io.StdIn.readLine()

    if (S.groupBy(identity).mapValues(_.length).forall(p => p._2 == 2))
      println("Yes")
    else
      println("No")
  }

}
