package jp.atcoder.past.sample.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine()

    def stacking(inputs: List[Char], stack: List[Char]): Int = {
      inputs match {
        case Nil =>
          S.length - stack.size
        case h :: t =>
          if (stack.isEmpty) stacking(t, h :: stack)
          else {
            h match {
              case '0' =>
                if (stack.head == '1') stacking(t, stack.tail)
                else stacking(t, '0' :: stack)
              case '1' =>
                if (stack.head == '0') stacking(t, stack.tail)
                else stacking(t, '1' :: stack)
            }
          }
      }
    }

    println(stacking(S.toList, Nil))
  }

}
