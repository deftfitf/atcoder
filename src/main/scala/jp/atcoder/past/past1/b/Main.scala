package jp.atcoder.past.past1.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    println(A.sliding(2).map {
      case Array(a, b) =>
        if (a == b) "stay"
        else if (b < a) s"down ${a - b}"
        else s"up ${b - a}"
    }. mkString("\n"))
  }

}
