package jp.atcoder.abc081.d

object Main {

  val MAX = 1000000007L

  case class State(patterns: Long, lastBlock: Int) {
    def process(top: Char, bottom: Char): State =
      lastBlock match {
        case 1 if top == bottom => copy(patterns = (patterns * 2L) % MAX)
        case 1 if top != bottom => copy(patterns = (patterns * 2L) % MAX, lastBlock = 3)
        case 2 if top == bottom => copy(lastBlock = 1)
        case 2 if top != bottom => copy(patterns = (patterns * 3L) % MAX, lastBlock = 3)
        case 3 => copy(lastBlock = 2)
      }
  }
  object State {
    def init(top: Char, bottom: Char): State =
      if (top == bottom) State(3, 1) else State(6, 3)
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val _ = sc.nextInt(); sc.nextLine()
    val S1, S2: Array[Char] = sc.nextLine().toCharArray

    // ブロックの並びは固定なので, 色を並び替えた場合の通りのみを考える
    // 決める時に左から決めていけばいい
    // 一個左のブロックの状況で,次おけるブロックの色が定まる
    // * 縦r
    // * 縦g
    // * 縦b
    // * 横rg
    // * 横rb
    // * 横gr
    // * 横gb
    // * 横br
    // * 横bg
    // (横に並ぶブロック x 2) => (横に並ぶブロック x 2) が現れた際には, 最初の2つのブロックの色が決定した時, 3通りの配置が可能
    // r,g r,b r,g
    // g,r g,r g,b
    // (横に並ぶブロック x 2) => (縦のブロック) が現れた際には, 最初の2つのブロックの色が決定した時, 1通りの配置が可能
    // r,b
    // g,b
    // (縦のブロック) => (横に並ぶブロック x 2) が現れた際には, 縦のブロックの色が決定した時, 2通りの配置が可能
    // r,
    // r,
    // (縦のブロック) => (縦のブロック) が現れた際には, 縦のブロックの色が決定したあと, 2通りの配置が可能
    // r,b r,g
    // 初回のやつだけ条件が異なる
    // 初回の縦ブロックは, 3通り置ける
    // 初回の横2つブロックは, 6通り置ける
    // 以降は, 前述の条件にしたがって掛け算で増えていく
    // なので, 左から見ていって, reduceしていけばいい

    val S = (S1 zip S2)
    val state = State.init(S.head._1, S.head._2)
    val result = S.tail.foldLeft(state)((state, next) => state.process(next._1, next._2)).patterns
    println(result)
  }

}
