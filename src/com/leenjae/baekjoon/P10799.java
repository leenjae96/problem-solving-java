package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준- 쇠막대기- 실버2(스택)
 *
 * @author leenjae96
 * @date 26.04.09
 */
public class P10799 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        int answer = 0;

        String line = br.readLine();
        char[] array = line.toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean canLazer = false; //바로 이전것이 '('면 바로 레이져가 될수 있음으로 판단.
        for (char ele : array) {
            if (ele == '(') {
                stack.push(ele);
                canLazer = true;
            } else {
                stack.pop();
                if(canLazer) { // 바로 이전 것이 '('이었으면 lazer라고 판단.
                    canLazer = false;
                    answer += stack.size();
                    continue;
                }
                answer++;
            }
        }

        System.out.println(answer);
    }
}
