package jp.microad.atcoder.abc110.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S, T = sc.nextLine()
    val to = scala.collection.mutable.Map.empty[Char, Char]
    val used = scala.collection.mutable.Set.empty[Char]
    val res = S.zip(T).forall { case (s, t) =>
      if (to.contains(s)) {
        to(s) == t
      } else {
        if (!used.contains(t)) {
          to.put(s, t)
          used.add(t)
          true
        } else false
      }
    }
    println(if (res) "Yes" else "No")
  }

}
