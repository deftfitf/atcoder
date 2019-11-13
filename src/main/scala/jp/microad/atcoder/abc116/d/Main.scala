package jp.microad.atcoder.abc116.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, K = sc.nextInt()
    
    val _Sushis = new Array[(Int, Long)](N)
    (0 until N) foreach { i =>
      val t = sc.nextInt()
      val d = sc.nextLong()
      _Sushis(i) = (t, d)
    }
    val Sushis = _Sushis.sortBy(_._2)(Ordering.Long.reverse)

    // f(a)とそれを構成するS_aがわかっている時に
    // f(a+1)とそれを構成するS_a+1をを求めることを考えます(x <= a <= N-1)
    // S_a+1はS_aに以下の操作を加えることで構成することができます
    // 1. 集合S_aから、取り除いても「ネタ」の種類数が減らない寿司の中で最も「おいしさ」の小さいものを取り除く
    //    この時点でネタの種類数が減らないものがない場合には, それまでのものから選ぶ
    // 2. 集合S_aに含まれない寿司の中で、集合S_aにない「ネタ」を持つ、最も「おいしさ」の大きいものをS_aに加える
    // この操作を繰り返すことにより、f(i) (x < i <= N)を再帰的に求めることができます。

    val (initialSushis, restSushis) = Sushis.splitAt(K)
    val sushiset = Set(initialSushis.map(_._1): _*)
    var p = sushiset.size.toLong
    val initialV = initialSushis.foldLeft(0L)(_ + _._2) + p * p

    val removableSushis = scala.collection.mutable.PriorityQueue[(Int, Long)]()(Ordering.by[(Int, Long), Long](_._2).reverse)
    val appendableSushis = scala.collection.mutable.PriorityQueue[(Int, Long)]()(Ordering.by[(Int, Long), Long](_._2))

    {
      val spSushis = initialSushis.sorted(Ordering.Tuple2(Ordering.Int, Ordering.Long.reverse))
      val N = initialSushis.length
      var bef = -1
      var i = 0
      while (i < N) {
        val sushi = spSushis(i)
        if (sushi._1 == bef) {
          removableSushis.enqueue(sushi)
        }
        bef = sushi._1
        i += 1
      }
    }

    {
      val spSushis = restSushis.sorted(Ordering.Tuple2(Ordering.Int, Ordering.Long.reverse))
      val N = spSushis.length
      var bef = -1
      var i = 0
      while (i < N) {
        val sushi = spSushis(i)
        if (!sushiset.contains(sushi._1)) {
          if (sushi._1 != bef) {
            appendableSushis.enqueue(sushi)
          }
          bef = sushi._1
        }
        i += 1
      }
    }

    def solve(bef: Long, max: Long, p: Long): Long =
      if (p < K && removableSushis.nonEmpty && appendableSushis.nonEmpty) {
        val rs = removableSushis.dequeue()
        val as = appendableSushis.dequeue()
        val current = bef - rs._2 + as._2 + 2 * p + 1
        solve(current, max max current, p + 1)
      } else max

    println(solve(initialV, initialV, p))
  }

}
