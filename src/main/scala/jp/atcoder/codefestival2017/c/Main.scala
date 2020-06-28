package jp.atcoder.codefestival2017.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val H, W = sc.nextInt()
    sc.nextLine()
    val A: Array[Array[Char]] = Array.fill(H)(sc.nextLine().toCharArray)

    // 各i について, ai,j = ai,W+1-j = aH+1-i,j = aH+1-i,W+1-j
    // つまり, 各文字について四隅に文字列を配置する必要がある
    def solve(H: Int, W: Int, A: Array[Array[Char]]): Boolean = {
      // mod % 4に対するカウント, % 4 != 0の時のみ, % 4の値を最後に+1する
      val charSizes =
        A.flatten.groupBy(identity).map(_._2.length)
      val groupCounts = new Array[Int](4)
      for (elem <- charSizes) {
        groupCounts(0) += elem / 4
        if (elem % 4 != 0) groupCounts(elem % 4) += 1
      }

      if (H % 2 == 0 && W % 2 == 0) {
        4 * groupCounts(0) == H * W
      } else if (H % 2 == 0 && W % 2 == 1) {
        (4 * groupCounts(0)) >= (H * (W - 1)) && {
          val twoCounts = (if ((4 * groupCounts(0)) > (H * (W - 1))) 2 * ((4 * groupCounts(0)) - (H * (W - 1))) / 4 else 0) + (groupCounts(2))
          2 * twoCounts == H
        }
      } else if (H % 2 == 1 && W % 2 == 0) {
        (4 * groupCounts(0)) >= (W * (H - 1)) && {
          val twoCounts = (if ((4 * groupCounts(0)) > (W * (H - 1))) 2 * ((4 * groupCounts(0)) - (W * (H - 1))) / 4 else 0) + (groupCounts(2))
          2 * twoCounts == W
        }
      } else {
        (4 * groupCounts(0)) >= ((W - 1) * (H - 1)) && {
          {
            val twoCounts = (if ((4 * groupCounts(0)) > ((W - 1) * (H - 1))) 2 * ((4 * groupCounts(0)) - ((W - 1) * (H - 1))) / 4 else 0) + (groupCounts(2))
            2 * twoCounts == (H - 1) + (W - 1) && groupCounts(1) == 1
          } || {
            val twoCounts = (if ((4 * groupCounts(0)) > ((W - 1) * (H - 1))) 2 * ((4 * groupCounts(0)) - ((W - 1) * (H - 1))) / 4 else 0) + (groupCounts(2))
            2 * twoCounts == (H - 1) + (W - 1) - 1 && groupCounts(3) == 1
          }
        }
      }
    }

    val res = if (solve(H, W, A)) "Yes" else "No"
    println(res)
  }

}
