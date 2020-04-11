package jp.atcoder.abc155.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    if (Array.fill(3)(sc.nextInt()).toSet.size == 3) {
      println("No")
    } else {
      println("Yes")
    }

  }

}
