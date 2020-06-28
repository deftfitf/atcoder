package jp.atcoder.abc048.b

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    // b以下のxの倍数の個数 - a以下のxの倍数の個数
    val a, b, x = sc.nextLong()
    val result = (b / x) - (a / x) + (if (a % x == 0) 1 else 0)
    println(result)
  }

}
