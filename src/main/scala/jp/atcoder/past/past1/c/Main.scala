package jp.atcoder.past.past1.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    println(Array.fill(6)(sc.nextInt()).sorted(Ordering.Int.reverse).take(3).last)
  }

}
