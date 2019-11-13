package playground


object Main {

  def printArray(arr: Array[Int]): Unit =
    println(arr.mkString("\n"))

  def main(args: Array[String]): Unit = {
    import scala.collection.immutable.Queue
    val sc = new java.util.Scanner(System.in)

    val N = sc.nextInt()

    def solve(): Long = {
      val MD = 1000000000 + 7
      val memo = Array.fill(N)(scala.collection.mutable.Map.empty[Queue[Char], Long])

      def dfs(i: Int, last3: Queue[Char]): Long =
        if (i < N)
          if (memo(i).contains(last3))
            memo(i).apply(last3)
          else {
            val dequeued = last3.dequeue._2
            val v = last3 match {
              case Queue(_, 'A', 'G') =>
                (dfs(i + 1, dequeued.enqueue('A')) +
                  dfs(i + 1, dequeued.enqueue('G')) +
                  dfs(i + 1, dequeued.enqueue('T'))) % MD
              case Queue(_, 'A', 'C') =>
                (dfs(i + 1, dequeued.enqueue('A')) +
                  dfs(i + 1, dequeued.enqueue('C')) +
                  dfs(i + 1, dequeued.enqueue('T'))) % MD
              case Queue(_, 'G', 'A') =>
                (dfs(i + 1, dequeued.enqueue('A')) +
                  dfs(i + 1, dequeued.enqueue('G')) +
                  dfs(i + 1, dequeued.enqueue('T'))) % MD
              case Queue('A', _, 'G') =>
                (dfs(i + 1, dequeued.enqueue('A')) +
                  dfs(i + 1, dequeued.enqueue('G')) +
                  dfs(i + 1, dequeued.enqueue('T'))) % MD
              case Queue('A', 'G', _) =>
                (dfs(i + 1, dequeued.enqueue('A')) +
                  dfs(i + 1, dequeued.enqueue('G')) +
                  dfs(i + 1, dequeued.enqueue('T'))) % MD
              case _ =>
                (dfs(i + 1, dequeued.enqueue('A')) +
                  dfs(i + 1, dequeued.enqueue('C')) +
                  dfs(i + 1, dequeued.enqueue('G')) +
                  dfs(i + 1, dequeued.enqueue('T'))) % MD
            }
            memo(i).put(last3, v)
            v
          }
        else 1

      dfs(0, Queue('T', 'T', 'T'))
    }

    println(solve())
  }

}
