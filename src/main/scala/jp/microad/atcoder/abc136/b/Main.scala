package jp.microad.atcoder.abc136.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()

    println((1 to N).count{ n =>
      n.toString.length % 2 == 1
    })
  }

}
