package ant.bit

import scala.reflect.ClassTag

class BIT2D[T: Group: ClassTag] private(n: Int, m: Int) {

  implicit val g = implicitly[Group[T]]
  private[this] val bit2d: Array[Array[T]] =
    Array.fill(n, m)(g.zero)

  def add(a: Int, b: Int, value: T): Unit =
    loop(a, _ <= n, a => a + (a & -a)) { y =>
      loop(b, _ <= m, b => b + (b & -b)) { x =>
        bit2d(y)(x) = g.op(bit2d(y)(x), value)
      }
    }

  def sum(a: Int, b: Int): T = {
    var ret = g.zero
    loop(a, _ > 0, a => a - (a & -a)) { y =>
      loop(b, _ > 0, b => b - (b & -b)) { x =>
        ret = g.op(ret, bit2d(y)(x))
      }
    }
    ret
  }

  private def loop(i: Int, cond: Int => Boolean, update: Int => Int)
                  (f: Int => Unit): Unit =
    if (cond(i)) { f(i); loop(update(i), cond, update)(f) }

}

object BIT2D {

  def apply[T: Group: ClassTag](n: Int, m: Int): BIT2D[T] =
    new BIT2D[T](n, m)

}