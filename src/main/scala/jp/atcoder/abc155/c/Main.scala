package jp.atcoder.abc155.c

import scala.collection.mutable

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt(); sc.nextLine()
    val S = Array.fill(N)(sc.nextLine())

    val m = mutable.Map[String, Int]()
    S.foreach(s => {
      m.update(s, m.getOrElseUpdate(s, 0) + 1)
    })
    val max = m.maxBy(_._2)._2
    println(m.filter(_._2 == max).toSeq.map(_._1).sorted.mkString("\n"))
  }

}
