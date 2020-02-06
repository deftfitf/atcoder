package ant.bit

trait Monoid[A] {
  def zero: A
  def op(a1: A, a2: A): A
}

trait Group[A] extends Monoid[A] {
  def inverse(a: A): A
}