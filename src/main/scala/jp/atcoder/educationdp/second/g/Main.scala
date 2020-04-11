package jp.atcoder.educationdp.second.g

import scala.collection.mutable.ArrayBuffer

//object Main {
//
//  def main(args: Array[String]): Unit = {
//    val sc = new java.util.Scanner(System.in)
//
//    val V, E = sc.nextInt()
//    val G = Array.fill(V)(new ArrayBuffer[Int]())
//    for (i <- 0 until E) {
//      val x, y= sc.nextInt()
//      G(x-1).append(y-1)
//    }
//
//    // ある頂点から入って, 最長の有向パスの長さはDFSをすれば手に入る
//    // dp[v] := 頂点vから入った時の最長の有向パスの長さとすれば
//    //          他の頂点から入った時, すでにdp[v]が計算されていれば, その長さを追加すればいい
//    // これで, 全頂点と全辺を１回ずつ通るので, O(V+E)
//    def solve(V: Int, G: Array[ArrayBuffer[Int]]): Int = {
//
//    }
//
//    println(solve(V, G))
//  }
//
//}
