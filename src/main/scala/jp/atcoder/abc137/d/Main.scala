package jp.atcoder.abc137.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val N, M = sc.nextInt()
    case class Job(after: Int, money: Int)
    val _JOB = new Array[Job](N)
    (0 until N) foreach { i =>
      val a, b = sc.nextInt()
      _JOB(i) = Job(a, b)
    }

    val JOB: Map[Int, Array[Job]] = _JOB.groupBy(M - _.after)
    // 1日後にもらえる場合, 3日目以前のどこでもやれる.
    // つまり(M - Bi)日以前のどこでもやれる.

    // Aの値が小さければ小さいほど自由度が高い.
    // 各バイト毎に, x日目までにやらないとだめ, という期限がある.
    // 後ろの日からどのアルバイトをすればいいか決めていく.

    var priorityQueue = scala.collection.mutable.PriorityQueue[Job]()(Ordering.by(_.money))
    var day = M - 1
    var MAX = 0
    while (day >= 0) {
      if (JOB.contains(day)) {
        val jobs = JOB(day)
        priorityQueue.enqueue(jobs: _*)
      }
      if (priorityQueue.nonEmpty) {
        MAX += priorityQueue.dequeue().money
      }
      day -= 1
    }
    println(MAX)
  }

}
