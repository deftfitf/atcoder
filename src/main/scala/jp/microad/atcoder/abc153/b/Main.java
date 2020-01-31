package jp.microad.atcoder.abc153.b;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new java.util.Scanner(System.in);

        int H = sc.nextInt();
        int N = sc.nextInt();
        int[] A = new int[N];
        Arrays.setAll(A, i -> sc.nextInt());

        int sum = Arrays.stream(A).sum();
        if (sum >= H) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
