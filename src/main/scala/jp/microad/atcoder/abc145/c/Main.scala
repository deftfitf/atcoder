package jp.microad.atcoder.abc145.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val _ = sc.nextLine()
    val S = sc.nextLine()

    def zip(zipped: List[Char], lst: List[Char]): List[Char] = {
      lst match {
        case Nil => zipped.reverse
        case h :: t =>
          zipped match {
            case Nil => zip(h :: Nil, t)
            case zh :: _ =>
              if (h == zh) {
                zip(zipped, t)
              } else {
                zip(h :: zipped, t)
              }
          }
      }
    }

    println(zip(Nil, S.toList).size)
  }

}
