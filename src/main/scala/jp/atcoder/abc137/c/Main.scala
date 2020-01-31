package jp.atcoder.abc137.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt(); sc.nextLine()
    val S = Array.fill(N)(sc.nextLine().sorted)
    val count =
      scala.collection.mutable.Map[String, Int]() ++
        S.groupBy(identity).mapValues(_.length - 1)

    var r = BigInt(0)
    var i = 0
    while (i < N) {
      val c = count(S(i))
      if (c > 0) {
        r += c
        count.update(S(i), c - 1)
      }
      i += 1
    }

    println(r)
  }

}