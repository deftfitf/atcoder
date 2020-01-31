package jp.atcoder.abc153.e;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int H = sc.nextInt();
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            B[i] = sc.nextInt();
        }

        // 何度でも同じ魔法が使えるので, 基本同じ魔法しか使わない(はず)
        // 魔法の種類がN(<=10^3).
        // 普通に全ての魔法について, その魔法が最も効率の高い魔法であるという前提の元,
        // 攻撃を行う。すると, 最後の一撃に関してだけは, それよりも効率のいい一撃が存在する
        // 可能性があることに気がつく。
        // そのような魔法は, 残りの体力以上の攻撃力を持つ最も魔力の小さな攻撃である
        // (その中には当該魔法も含む)
        // 攻撃力の降順に魔法をソートしておくと,
        // dp[i] := iまでの魔法の中で最も魔力の小さなもの
        // を計算しておくことでこれを高速に導出できる.

//        int[] idx = IntStream.range(0, N)
//                .boxed().sorted(Comparator.comparing(i -> A[i]))
//                .mapToInt(i -> i).toArray();
//        int[] dp = new int[N+1];
//        dp[N] = Integer.MAX_VALUE;
//        for (int i = N - 1; i >= 0; i--) {
//            dp[i] = Math.min(B[idx[i]], dp[i+1]);
//        }
//
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < N; i++) {
//            int count = (H / A[i]);
//            int residue = (H % A[i]);
//            if (residue > 0) {
//                int j = binarySearch(idx, A, residue, -1, N - 1);
//                min = Math.min(min, dp[j] + count * B[i]);
//            } else {
//                min = Math.min(min, count * B[i]);
//            }
//        }
//
//        System.out.println(min);

        // 実際のところ, 最後の一手を最初に与えるべきっぽい気がした.
        // 回答ではdpを使っているが,使わなくてもいけそうなきがする.
        // その場合, ある効率の良いと考えられる魔法について,
        // 最初の一手を全ての魔法の中から選ぶ必要があり,
        // O(N^2) で今回 N(<=10^3) なので,間に合うか微妙?
//        int min = Integer.MAX_VALUE;
//        for (int last = 0; last < N; last++) {
//            for (int best = 0; best < N; best++) {
//                min =
//                    Math.min(min, B[last] +
//                        (int)Math.ceil((H - A[last]) / (double)A[best]) * B[best]);
//            }
//        }
//
//        System.out.println(min);

        // どうやら, 二種類でいける訳ではないので結局dpが必要
        // dp[i] := iダメージを与えるのに必要な最小の魔力
        int[] dp = new int[H+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int h = 0; h <= H; h++) {
            if (dp[h] == Integer.MAX_VALUE) continue;
            for (int i = 0; i < N; i++) {
                if (h+A[i] >= H) {
                    dp[H] = Math.min(dp[H], dp[h] + B[i]);
                } else {
                    dp[h+A[i]] = Math.min(dp[h+A[i]], dp[h] + B[i]);
                }
            }
        }

        System.out.println(dp[H]);
    }

//    private static int binarySearch(int[] idx, int[] A, int N, int left, int right) {
//        if (right - left > 1) {
//            int mid = left + (right - left) / 2;
//            if (A[idx[mid]] >= N) return binarySearch(idx, A, N, left, mid);
//            else return binarySearch(idx, A, N, mid, right);
//        } else {
//            return right;
//        }
//    }
}
