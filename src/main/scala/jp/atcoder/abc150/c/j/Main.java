package jp.atcoder.abc150.c.j;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] P = new int[N];
        int[] Q = new int[N];

        // 全通り試しても余裕で間に合う.
        // 全列挙して二分探索で求める
        // Scalaなら, (1 to N).permutations.toIndexedSeq.sortedで列挙して
        // 二分探索すればいいが..
    }
}
