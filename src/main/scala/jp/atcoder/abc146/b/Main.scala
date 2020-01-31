package jp.atcoder.abc146.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray
    val N = sc.nextInt(); sc.nextLine()
    val S = sc.nextLine()
    val R = S.map(c => alphabets((alphabets.indexOf(c) + N) % 26)).mkString
    println(R)
  }

}
