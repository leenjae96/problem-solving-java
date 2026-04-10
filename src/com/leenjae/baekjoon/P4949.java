package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준- 균형잡힌 세상- 실버4(스택)
 *
 * @author leenjae96
 * @date 26.04.09
 */
public class P4949 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        String line = br.readLine();
        while (!line.equals(".")) {
            char[] array = line.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (char ele : array) {
                if (ele == '.') break;
                else if (ele == '(' || ele == '[') {
                    stack.push(ele);
                } else if (ele == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        stack.push('.');
                        break;
                    }
                } else if (ele == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        stack.push('.');
                        break;
                    }
                }
            }

            if (stack.isEmpty()) {
                sb.append("yes").append("\n");
            } else {
                sb.append("no").append("\n");
            }

            line = br.readLine();
        }
        System.out.println(sb);
    }
}
