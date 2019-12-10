package ant.poj1064

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    val L = Array.fill(N)(sc.nextDouble())
    val L_MAX = 1000000F

    // 条件C(x) = 長さxの紐をK本取り出すことができる
    // とすると, C(x)を満たすような最大のxを求める問題になる
    // floor(Li / x)本であるので
    // C(x) = (floor(Li / x)の総和がK以上)となりO(N)で判定可能
    // O(N log L)

    def upperBound(left: Float, right: Float, f: Float => Boolean): Float = {
      def recursive(left: Float, right: Float): Float =
        if (right - left > 0.01) {
          val mid = left + (right - left) / 2.0F
          if (f(mid)) recursive(mid, right)
          else recursive(left, mid)
        } else left
      recursive(left, right)
    }

    val res = upperBound(0, L_MAX, x => L.map(l => Math.floor(l / x)).sum >= K)
    println("%.2f".format(res))
  }

}
