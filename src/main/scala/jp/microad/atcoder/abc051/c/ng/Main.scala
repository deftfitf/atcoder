package jp.microad.atcoder.abc051.c.ng

// TODO: まだNG
object Main {

  import scala.collection.immutable.Queue

  case class Point(x: Int, y: Int) {
    def move(dx: Int, dy: Int): Point =
      Point(x + dx, y + dy)
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val delta = 1000
    val FIELD = 2 * delta
    val sx, sy, tx, ty = delta + sc.nextInt()

    def solve(): Vector[Point] = {
      val visited = Array.fill(FIELD + 1, FIELD + 1)(false)

      def bfs(vector: Seq[(Int, Int)], start: Point, end: Point): Vector[Point] = {
        val localVisited = Array.fill(FIELD + 1, FIELD + 1)(false)

        def isVisited(x: Int, y: Int): Boolean =
          visited(y)(x) || localVisited(y)(x)

        def loop(queue: Queue[(Point, Vector[Point])]): Vector[Point] =
          if (queue.nonEmpty) {
            val (point, path) = queue.head
            if (point.x >= 0 && point.x <= FIELD && point.y >= 0 && point.y <= FIELD) {
              if (point.x == end.x && point.y == end.y) {
                path foreach (point => visited(point.y)(point.x) = true)
                path
              } else if ((point.x == sx && point.y == sy) ||
                (point.x == tx && point.y == ty) ||
                !isVisited(point.x, point.y)) {
                localVisited(point.y)(point.x) = true
                loop(
                  queue.tail.enqueue(vector.map { case (dx, dy) =>
                    (point.move(dx, dy), path :+ point)
                  }.toList))
              } else loop(queue.tail)
            } else loop(queue.tail)
          } else Vector.empty // not reachable
        loop(Queue((Point(start.x, start.y), Vector.empty)))
      }

      val vector = Seq((0, 1), (1, 0), (0, -1), (-1, 0))

      bfs(vector, Point(sx, sy), Point(tx, ty)) ++
        bfs(vector, Point(tx, ty), Point(sx, sy)) ++
        bfs(vector, Point(sx, sy), Point(tx, ty)) ++
        bfs(vector, Point(tx, ty), Point(sx, sy)) :+ Point(sx, sy)
    }

    println(solve().sliding(2).map { p =>
      val parent = p.head
      val child = p.tail.head
      (child.x - parent.x, child.y - parent.y) match {
        case (0, 1) => 'U'
        case (1, 0) => 'R'
        case (0, -1) => 'D'
        case (-1, 0) => 'L'
      }
    }. mkString)
  }

}
