package jp.microad.atcoder.abc093.c.j;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N+2];
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
        }

        int S = 0;
        for (int i = 1; i <= N + 1; i++) {
            S += Math.abs(A[i] - A[i - 1]);
        }

        String[] R = new String[N];
        for (int i = 1; i <= N; i++) {
            R[i - 1] = String.valueOf(
                    S - (Math.abs(A[i] - A[i - 1]) + Math.abs(A[i + 1] - A[i])) +
                    Math.abs(A[i + 1] - A[i - 1]));
        }

        System.out.println(String.join("\n", R));
    }

}
