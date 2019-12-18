package jp.microad.atcoder.abc075.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, A, B = sc.nextInt()
    val H = Array.fill(N)(sc.nextInt())

    // 貪欲法？
    // 常に一番体力が大きそうなモンスターを中心に攻撃していけば良さそう
    // 一番体力の大きなモンスターを効率よく選択する為には最大ヒープを使えば良さそう
    // まず, 体力を大きい順に優先度付したモンスターを全てぶち込む
    // すると、先頭のモンスターが一番体力が大きいので、最大ヒープから取り出し
    // そのモンスターを中心に攻撃する。それ以外のモンスターには均一にBだけダメージを与えるので
    // 全体としてのヒープ性は保たれる。全てのモンスターの体力が0になると終了する
    // 攻撃が終わった後, 再度モンスターをヒープに入れる. この時、ヒープが再計算される

    // 第一案.
    // これだと, 最悪計算量, 攻撃が10^9回しなければならないので, 時間が間に合わない
    // もうちょい効率のいい方法を考える必要がある
//    def solve(N: Int, A: Int, B: Int, H: Array[Int]): Int = {
//      import scala.collection.mutable
//      val priorityQueue =
//        mutable.PriorityQueue[Int](0 until N: _*)(
//          Ordering.by[Int, Int](H))
//      @scala.annotation.tailrec
//      def loop(count: Int): Int =
//        if (H.exists(_ > 0)) {
//          val i = priorityQueue.dequeue()
//
//          H(i) -= A
//          for {
//            j <- 0 until N
//            if i != j
//          } H(j) -= B
//
//          priorityQueue += i
//          loop(count + 1)
//        } else count
//      loop(0)
//    }

    // 第二案,
    // x回攻撃した時に, 全ての魔物を倒せるか倒せないかを C(x)とする
    // そうした時, x * B のダメージを全ての敵に対して与えることができる
    // また, A > B から (A - B)を1体に与え, Bを全体に与えるとみなすことができ
    // (A - B)をどの順番で攻撃しても変わりないので、
    // 一回の判定で O(N) で, 二分探索分で O(N log M)で計算できるので間に合う
    def solve(N: Int, A: Long, B: Long, H: Array[Int]): Int = {
      val C = A - B
      val M = Math.pow(10, 9).toInt

      def check(x: Int): Boolean =
        H.foldLeft(0L)((b, h) =>
          b + (((h - x * B) max 0L) / C.toDouble).ceil.toLong) <= x

      def upperBounds(): Int = {
        @scala.annotation.tailrec
        def loop(left: Int, right: Int): Int =
          if (right - left > 1) {
            val mid = left + (right - left) / 2
            if (check(mid)) loop(left, mid)
            else loop(mid, right)
          } else right
        loop(-1, M+1)
      }

      upperBounds()
    }

    println(solve(N, A, B, H))
  }

}
