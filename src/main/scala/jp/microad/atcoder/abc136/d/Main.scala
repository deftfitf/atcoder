package jp.microad.atcoder.abc136.d

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val S = sc.nextLine()

    // 繰り返す操作が十分に大きいので, 同じ文字が連続している場合には,
    // 同じ文字が連続していないところまで移動し, 振動を繰り返す
    // 左端がR, 右端がLなので, 子供達が端っこに寄ってしまうようなケースは無い.
    // 必ず振動する

    val RC = Array.fill(S.length)(-1)
    var i = S.length - 1
    var count = 0
    while (i >= 0) {
      if (S(i) == 'R') {
        count += 1
        RC(i) = count
      } else {
        count = 0
      }
      i -= 1
    }

    val LC = Array.fill(S.length)(-1)
    i = 0
    count = 0
    while (i < S.length) {
      if (S(i) == 'L') {
        count += 1
        LC(i) = count
      } else {
        count = 0
      }
      i += 1
    }

    i = 0
    val C = Array.fill(S.length)(0)
    while (i < S.length) {
      if (RC(i) != -1) {
        if (RC(i) % 2 == 0) {
          C(i + RC(i)) += 1
        } else {
          C(i + RC(i) - 1) += 1
        }
      }
      if (LC(i) != -1) {
        if (LC(i) % 2 == 0) {
          C(i - LC(i)) += 1
        } else {
          C(i - LC(i) + 1) += 1
        }
      }
      i += 1
    }
    println(C.mkString(" "))
  }

}
