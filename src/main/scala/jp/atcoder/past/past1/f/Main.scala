package jp.atcoder.past.past1.f

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine()

    def solve(S: String): String = {
      val wordsRegex = """([A-Z][a-z]*[A-Z])""".r
      wordsRegex.findAllIn(S).toSeq.sortBy(_.toLowerCase).mkString
    }

    println(solve(S))
  }

}
