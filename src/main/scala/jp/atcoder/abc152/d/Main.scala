package jp.atcoder.abc152.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()

    // 先頭に0のつかない10進数表記で表した時に, Aの末尾の桁が
    // Bの先頭の桁に等しく, Aの先頭の桁が, Bの末尾の桁に等しい組みの数
    // c_{i,j} := kの先頭の桁の数はiに等しく, 末尾の桁の数はjに等しい数
    def solve(N: Int): Int = {
      val c = Array.fill(10, 10)(0)
      for {
        i <- 1 to N
        iS = i.toString
        h = iS.head - '0'
        l = iS.last - '0'
      } c(h)(l) += 1

      (for {
        h <- 1 to 9
        l <- 1 to 9
      } yield c(h)(l) * c(l)(h)).sum
    }

    println(solve(N))
  }

}
