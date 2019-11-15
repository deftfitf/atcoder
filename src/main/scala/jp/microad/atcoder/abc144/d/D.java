package jp.microad.atcoder.abc144.d;

import java.util.Scanner;

public class D {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt(); int b = sc.nextInt(); int x = sc.nextInt();
        double h = x / ((double)a * (double)a);
        if (h >= b / 2.0) {
            System.out.println(Math.atan2(b - h, a / 2.0) * 180.0 / Math.PI);
        } else {
            System.out.println(Math.atan2(b, 2.0 * x / ((double)a * (double)b)) * 180.0 / Math.PI);
        }
    }

}
