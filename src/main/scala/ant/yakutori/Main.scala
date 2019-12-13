package ant.yakutori

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val N, S = sc.nextInt()
    val A = Array.fill(N)(sc.nextInt())

    // 区間の先頭と末尾を交互に進めながら条件を満たす最小の区間を求める手法を俗にしゃくとり法という
    def yakutori(N: Int, S: Int, A: Array[Int]): Int = {
      def loop(acm: Int, left: Int, right: Int, min: Int): Int = {
        val (a1, r1) = moveRight(acm, left, right)
        val (a2, l1) = moveLeft(a1, left, r1)
        if (l1 == left && r1 == right) min
        else loop(a2, l1, r1, min min (r1 - l1))
      }
      def moveRight(acm: Int, left: Int, right: Int): (Int, Int) =
        if (right < N)
          if (acm + A(right) < S) moveRight(acm + A(right), left, right + 1)
          else (acm + A(right), right + 1)
        else (acm, right)
      def moveLeft(acm: Int, left: Int, right: Int): (Int, Int) =
        if (left < N && acm - A(left) >= S) moveLeft(acm - A(left), left + 1, right)
        else (acm, left)
      val r = loop(0, 0, 0, N+1)
      if (r > N) 0
      else r
    }

    println(yakutori(N, S, A))
  }

}
