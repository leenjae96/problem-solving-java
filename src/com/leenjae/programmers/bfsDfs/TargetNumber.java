package com.leenjae.programmers.bfsDfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 프로그래머스 - 깊이/너비 우선탐색 - level2
 * 타겟넘버
 *
 * @author leenjae96
 * @date 25.11.14
 */
public class TargetNumber {
    //dfs아래 방법과 같은데, 메소드 리턴 타입을 int 로 줘서 계산하면서 가느냐
    //아니면 전역변수 COUNT를 ++의 차이.

    //추가로 stack방법과 queue방법 추가.
    int COUNT = 0;
    int[] NUMBERS;
    int TARGET;

    public int solution(int[] numbers, int target) {
        NUMBERS = numbers;
        TARGET = target;
        //dfs(0, 0);
        //processByStack();
        processByQueue();

        return COUNT;
    }

    void dfs(int value, int depth) {
        if (depth == NUMBERS.length) {
            if (value == TARGET) {
                COUNT++;
            }
            return;
        }

        dfs(value + NUMBERS[depth], depth + 1);
        dfs(value - NUMBERS[depth], depth + 1);
    }

    void processByStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // value
        stack.push(0); // depth

        while (!stack.isEmpty()) {
            int depth = stack.pop();
            int value = stack.pop();

            if (depth == NUMBERS.length) {
                if(value == TARGET) {
                    COUNT++;
                }
            } else {
                stack.push(value + NUMBERS[depth]); // plus value
                stack.push(depth + 1); // depth
                stack.push(value - NUMBERS[depth]); // minus value
                stack.push(depth + 1); // depth
            }
        }

    }

    void processByQueue() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // value;
        queue.add(0); // depth;

        while (!queue.isEmpty()) {
            int value = queue.poll();
            int depth = queue.poll();

            if (depth == NUMBERS.length) {
                if(value == TARGET) {
                    COUNT++;
                }
            } else {
                queue.add(value + NUMBERS[depth]); // plus value
                queue.add(depth + 1); // depth
                queue.add(value - NUMBERS[depth]); // minus value
                queue.add(depth + 1); // depth
            }
        }
    }
}

/**
 * 프로그래머스 - 깊이/너비 우선탐색 - level2
 * 타겟넘버
 *
 * @author leenjae96
 * @date 25.11.13
 */
class TargetNumber2 {
    int[] NUMBERS;
    int TARGET;

    public int solution(int[] numbers, int target) {
        this.NUMBERS = numbers;
        this.TARGET = target;

        return dfs(0, 0);
    }

    int dfs(int current, int depth) {
        int count = 0;
        if (depth == NUMBERS.length) {
            if (current == TARGET) {
                count = 1;
            }
            return count;
        }
        count += dfs(current + NUMBERS[depth], depth + 1);
        count += dfs(current - NUMBERS[depth], depth + 1);
        return count;
    }
}
