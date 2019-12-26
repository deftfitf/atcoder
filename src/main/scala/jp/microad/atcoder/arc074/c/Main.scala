package jp.microad.atcoder.arc074.c

object Main {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)

    val H, W = sc.nextLong()

    // 三つのブロックを出来るだけ均等に分割したい
    // 完全に均等に分割できた場合 S_{max} - S_{min} = 0
    // min(S_{max} - S_{min}) を求める
    // しかも、ブロックは長方形に分割する必要がある
    // 明らかにO(HW)では間に合わないので,
    // ブロック上を1つずつ操作したりするのでは通らない
    // 一つ目の長方形の縦幅を全探索する
    // S_A = hWと決まる
    // 縦2つに分割するケースと横2つに分割するケースがある
    // 縦2つに分割するケース
    // h' = (H - h) / 2
    // S_B = h' * W, S_C = (H - h - h') * W
    // 横2つに分割するケース w = [W/2]
    // S_B = (H - h) * w, S_C = (H - h) * (W - w)
    // (S_A max S_B max S_C) - (S_A min S_B min S_C)
    // をそれぞれのケースで求める

    def solve(H: Long, W: Long): Long = {
      def calc(H: Long, W: Long): Long = {

        def case1(S_A: Long, h: Long): Long = {
          val hh = (H - h) / 2L
          val S_B = hh * W
          val S_C = (H - h - hh) * W
          (S_A max S_B max S_C) - (S_A min S_B min S_C)
        }

        def case2(S_A: Long, h: Long): Long = {
          val w = W / 2L
          val S_B = (H - h) * w
          val S_C = (H - h) * (W - w)
          (S_A max S_B max S_C) - (S_A min S_B min S_C)
        }

        def loop(h: Long, min: Long): Long =
          if (h <= H - 1L) {
            val S_A = h * W
            loop(h + 1L, min min {
              if (h < H - 1) case1(S_A, h) else Int.MaxValue
            } min {
              case2(S_A, h)
            })
          } else min
        loop(1L, Int.MaxValue)
      }
      calc(H, W) min calc(W, H)
    }

    println(solve(H,W))
  }

}
