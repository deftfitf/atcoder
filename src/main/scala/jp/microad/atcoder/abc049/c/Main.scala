package jp.microad.atcoder.abc049.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine().reverse.toList

    def loop(S: List[Char]): Boolean =
      S match {
        case Nil => true
        case 'r' :: 'e' :: 'm' :: 'a' :: 'e' :: 'r' :: 'd' :: t => loop(t)
        case 'm' :: 'a' :: 'e' :: 'r' :: 'd' :: t => loop(t)
        case 'r' :: 'e' :: 's' :: 'a' :: 'r' :: 'e' :: t => loop(t)
        case 'e' :: 's' :: 'a' :: 'r' :: 'e' :: t => loop(t)
        case _ => false
      }

    println(if (loop(S)) "YES" else "NO")
  }

}
