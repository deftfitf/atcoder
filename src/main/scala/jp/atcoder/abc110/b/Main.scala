package jp.atcoder.abc110.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M, X, Y = sc.nextInt()
    val x = Array.fill(N)(sc.nextInt())
    val y = Array.fill(M)(sc.nextInt())
    println((X + 1 to Y) exists { Z =>
      x.max < Z && Z <= y.min
    } match {
      case true => "No War"
      case false => "War"
    })
  }

}

