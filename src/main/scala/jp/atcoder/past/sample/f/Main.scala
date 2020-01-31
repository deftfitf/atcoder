package jp.atcoder.past.sample.f

import scala.collection.mutable

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt(); sc.nextLine()
    var sum = 0L
    val counter = mutable.Map[String, Long]()
    Array.fill(N)(sc.nextLine()).map(_.sorted).foreach { s =>
      sum += counter.getOrElseUpdate(s, 0L)
      counter(s) += 1L
    }
    println(sum)
  }

}
