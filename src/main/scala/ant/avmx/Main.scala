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
    val V_MAX = Math.pow(10, 6).toInt

    // 各品物を単位重さあたりの価値でソートして、大きい方から貪欲に選ぶ場合ではうまくいかない
    // C(x) := 単位重さあたりの価値がx以上になるように選ぶことができる
    def upperBound(left: Float, right: Float, f: Float => Boolean): Float = {
      def recursive(count: Int, left: Float, right: Float): Float =
        if (count < 100) {
          val mid = left + (right - left) / 2.0F
          if (f(mid)) recursive(count+1, mid, right)
          else recursive(count+1, left, mid)
        } else left
      recursive(0, left, right)
    }

    val range = 0 until N
    val f: Float => Boolean = x =>
      range.sortBy(i => V(i) - x * W(i))(Ordering.Float.reverse).take(K)
        .map(i => V(i) - x * W(i)).sum >= 0

    val res = upperBound(0, V_MAX+1, f)
    println("%.2f".format(res))
  }

}
