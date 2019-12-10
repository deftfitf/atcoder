package ant.avmx

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    // 重さと価値がそれぞれw, vであるようなn個の品物。
    // この中からちょうどk個選んだ時の単位重さあたりの価値の最大値
    val N, K = sc.nextInt()
    val W, V = new Array[Int](N)
    (0 until N) foreach { i =>
      val w, v = sc.nextInt()
      W(i) = w
      V(i) = v
    }

    // 各品物を単位重さあたりの価値でソートして、大きい方から貪欲に選ぶ場合ではうまくいかない
    // C(x) := 単位重さあたりの価値がx以上になるように選ぶことができる

  }

}
