package jp.atcoder.abc122.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val acgt = new Array[Boolean](26)
    Seq('A', 'C', 'G', 'T').foreach(c => acgt(c - 'A') = true)

    val S = sc.nextLine()
    val candidate = for {
      h <- 0 until S.length
      t <- h until S.length
      if (h to t).forall(i => acgt(S.charAt(i) - 'A'))
    } yield t - h + 1

    println(if (candidate.nonEmpty) candidate.max else 0)
  }

}
