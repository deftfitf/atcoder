package jp.atcoder.past.past1.e

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, Q = sc.nextInt(); sc.nextLine()
    val S = Array.fill(Q)(sc.nextLine().trim.split(" ").map(_.toInt - 1))
    val F = Array.fill(N, N)(false)

    S.foreach {
      case Array(0, a, b) =>
        F(a)(b) = true
      case Array(1, a) =>
        (0 until N).filter(b => F(b)(a)).foreach(b => F(a)(b) = true)
      case Array(2, a) =>
        (0 until N).filter(b => F(a)(b))
          .flatMap(b => (0 until N).filter(c => F(b)(c)))
          .filter(_ != a).foreach(c => F(a)(c) = true)
    }

    val r = F.map(_.map{
      case true => "Y"
      case false => "N"
    }.mkString).mkString("\n")

    println(r)
  }

}
