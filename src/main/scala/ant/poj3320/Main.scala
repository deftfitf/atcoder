package ant.poj3320

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val P = sc.nextInt()
    val A = Array.fill(P)(sc.nextInt())
    val K = A.toSet.size

    // 入ってきた数字は出すことができる,ページは一枚ずつ読み進める.
    // 区間の先頭を取り除いた際に、条件を満たすかの判定ができます
    // 区間の先頭からsを取り除く際には、ページsに書かれている事柄の出現数を1減らすことになり、
    // この際にその事柄の出現数が0になった場合には, 同じ事柄が出現するまで末尾tを進めてやればよいことになります。
    // 区間の末尾にtを追加する際にページtに書かれている事柄の出現数を1増やしていくことで、次の区間におkれう各事柄
    // の出現数も更新することができ、この操作を繰り返すことによりO(P log P)で最小の区間を求めることができます

    def yakutori(P: Int, K: Int, A: Array[Int]): Int = {
      import scala.collection.mutable
      val count = mutable.Map[Int, Int]()
      def loop(num: Int, left: Int, right: Int, min: Int): Int = {
        val (n1, r1) = moveRight(num, left, right)
        val (n2, l1) = moveLeft(n1, left, r1)
        if (left == l1 && right == r1) min
        else loop(n2, l1, r1, if (n1 == K) min min (r1 - left) else min)
      }
      def moveRight(num: Int, left: Int, right: Int): (Int, Int) =
        if (right < P) {
          val updatedNum = if (count.getOrElseUpdate(A(right), 0) == 0) num + 1 else num
          count(A(right)) += 1
          if (updatedNum < K) moveRight(updatedNum, left, right + 1)
          else (updatedNum, right + 1)
        } else (num, right)
      def moveLeft(num: Int, left: Int, right: Int): (Int, Int) =
        if (left < P) {
          count(A(left)) -= 1
          if (count(A(left)) <= 0) (num - 1, left + 1)
          else (num, left + 1)
        } else (num, left)
      loop(0, 0, 0, P+1)
    }

    println(yakutori(P, K, A))
  }

}
