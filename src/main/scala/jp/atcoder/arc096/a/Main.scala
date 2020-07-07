package jp.atcoder.arc096.a

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val A, B, C, X, Y = sc.nextInt()
    // X, Y のピザを用意する必要がある. 単に全探索すると, Aの枚数を決めるのに10^5乗とるので、間に合わない
    // 先にABピザを何枚手に入れるかを考えると, AとBのピザの枚数は絞られる
    // ABが決まると, 自動的にAとBのピザの枚数が決定する
    val r = (for {
      ab <- 0 to (X max Y) * 2
      a = (X - ab / 2) max 0
      b = (Y - ab / 2) max 0
    } yield (A * a + B * b + C * ab)).min

    println(r)
  }

}
