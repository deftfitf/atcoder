package jp.atcoder.abc143.c;

import java.util.Scanner;
import java.util.Stack;

public class C {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        sc.nextLine();
        char[] S = sc.nextLine().toCharArray();

        Stack<Character> stack = new Stack<>();
        for (char c : S) {
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            if (stack.peek() == c) {
                continue;
            }
            stack.push(c);
        }

        System.out.println(stack.size());
    }

}
