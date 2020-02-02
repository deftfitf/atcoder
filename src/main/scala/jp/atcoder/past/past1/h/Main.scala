package jp.atcoder.past.past1.h

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()
    val C = Array.fill(N)(sc.nextInt())
    val Q = sc.nextInt(); sc.nextLine()
    val S = Array.fill(Q)(sc.nextLine().trim.split("\\s+").map(_.toInt))

    val oddMin = (0 until N by 2).map(C).min
    val evenMin = if (N >= 2) (1 until N by 2).map(C).min else oddMin
    // 普通に毎回カード全件問い合わせるとO(NQ)なので,間に合わない
    // 偶数番号の最小と,奇数番号の最小を管理しておけば問題ない
    // また, 全体販売などの時に実際に配列の値を減少させると, 減少のためのコストがかかるので
    // 全体販売した枚数と, セット販売した枚数を記録しておく.
    val oddN = N / 2 + N % 2
    val result =
      S.foldLeft((0L, evenMin, oddMin, 0, 0))((st, next) =>
        next match {
          case Array(1, x, a) =>
            if (x % 2 == 0) {
              val current = C(x-1) - st._5
              if (current >= a) {
                C(x-1) -= a
                st.copy(_1 = st._1 + a, _2 = st._2 min (current - a))
              } else st
            } else {
              val current = C(x-1) - st._4 - st._5
              if (current >= a) {
                C(x-1) -= a
                st.copy(_1 = st._1 + a, _3 = st._3 min (current - a))
              } else st
            }
          case Array(2, a) =>
            if (st._3 >= a)
              st.copy(_1 = st._1 + a.toLong * oddN, _3 = st._3 - a, _4 = st._4 + a)
            else st
          case Array(3, a) =>
            if (st._2 >= a && st._3 >= a)
              st.copy(_1 = st._1 + a.toLong * N, _2 = st._2 - a, _3 = st._3 - a, _5 = st._5 + a)
            else st
        })._1

    println(result)
  }

}
