package com.leenjae.programmers.algorithm.stackQueue;

import java.util.Stack;

/**
 * 프로그래머스 - 스택/큐 - level2
 * 올바른 괄호
 *
 * @author leenjae96
 * @date 25.11.27
 */
public class ValidParenthesis {
    boolean solution(String s) {
        boolean answer = true;
        int length = s.length();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length ; i++) {
            if (s.charAt(i) == '(') {
                stack.push(1);
            } else {
                if (stack.isEmpty()) {
                    answer = false;
                    break;
                }
                stack.pop();
            }
        }
        if(!stack.isEmpty()) {
            answer = false;
        }
        return answer;
    }
}
