package jp.atcoder.abc150.b;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); sc.nextLine();
        String S = sc.nextLine();
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.startsWith("ABC", i)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
