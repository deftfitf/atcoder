package lib.graph

object BellmanFord {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val V, E = sc.nextInt()
    val edge = Array.fill(E) {
      val u, v, cost = sc.nextInt()
      Edge(u, v, cost)
    }

    allPointsPairBellmanFord(edge)(E, V) match {
      case Some(result) =>
        println(result.map(_.map {
          case DISTANCE_INF => "INF"
          case d => d.toString
        }. mkString(" ")).mkString("\n"))
      case None =>
        println("NEGATIVE CYCLE")
    }
  }

  final private[this] case class Edge(u: Int, v: Int, cost: Int)

  final private[this] def bellmanFord(
    edges: Seq[Edge],
    start: Int
  )(E: Int = edges.length, V: Int): Option[IndexedSeq[Long]] = {
    val distance = Array.fill[Long](V)(DISTANCE_INF)
    val parents = Array.fill(V)(PARENT_UNDEFINED)
    distance(start) = 0

    def relax(edge: Edge): Unit =
      if (distance(edge.u) != DISTANCE_INF &&
          distance(edge.v) > distance(edge.u) + edge.cost) {
        distance(edge.v) = distance(edge.u) + edge.cost
        parents(edge.v) = edge.u
      }

    var i = 0
    while (i < V - 1) {
      edges foreach relax
      i += 1
    }

    edges foreach { edge =>
      if (distance(edge.u) != DISTANCE_INF &&
          distance(edge.v) > distance(edge.u) + edge.cost)
        return None
    }
    Some(distance)
  }

  final private[this] def allPointsPairBellmanFord(
      edges: Seq[Edge]
  )(E: Int = edges.length, V: Int): Option[IndexedSeq[IndexedSeq[Long]]] = {
    import scala.collection.mutable
    (0 until V). foldLeft (Option(mutable.ArrayBuilder.make[IndexedSeq[Long]]())) { case (reduced, next) =>
      reduced match {
        case Some(builder) => bellmanFord(edges, next)(E, V) map builder.+=
        case None => None
      }
    } map (_.result())
  }

  final private[this] val DISTANCE_INF = Long.MaxValue / 2
  final private[this] val PARENT_UNDEFINED = -1

}
