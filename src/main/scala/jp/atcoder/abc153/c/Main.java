package jp.atcoder.abc153.c;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static void reverse(int[] arr) {
        int N = arr.length;
        int temp;
        for (int i = 0; i < N / 2; i++) {
            temp = arr[i];
            arr[i] = arr[N-i-1];
            arr[N-i-1] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] H = new int[N];
        Arrays.setAll(H, i -> sc.nextInt());
        Arrays.sort(H);
        reverse(H);
        // 大きい順に必殺技を適用して, 残ったモンスターのHPの合計 + K
        System.out.println(solve(N, K, H));
    }

    private static long solve(int N, int K, int[] H) {
        long turns = 0L;
        for (int i = K; i < N; i++) {
            turns += H[i];
        }
        return turns;
    }
}
