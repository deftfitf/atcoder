package jp.microad.atcoder.abc089.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt(); sc.nextLine()
    val S = Array.fill(N)(sc.nextLine())

    val candidates = S.groupBy(_(0)).filter {
      case (key, _) => key match {
        case 'M' | 'A' | 'R' | 'C' | 'H' => true
        case _ => false
      }
    }. mapValues(_.length)

    val result = candidates.keys.toSeq.combinations(3)
      .foldLeft(0L)((b, n) => b + n.foldLeft(1L)(_ * candidates(_)))

    println(result)
  }

}
