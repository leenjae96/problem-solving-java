package com.leenjae.programmers.algorithm.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * 프로그래머스 - 그리디 - level2
 * 큰 수 만들기
 *
 * @author leenjae96
 * @date 25.11.28
 */
public class MakingBiggestNumber {
    public String solution(String number, int k) {
        int resultLength = number.length() - k;
        char[] charArray = number.toCharArray();
        StringBuilder sb = new StringBuilder();
        //숫자 배열에서 k를 빼고 로 가장 멀리갈수 있는 subString 중에 가장 큰 값을 찾는다.
        // 그 인덱스를 기억하고 앞까지의 숫자는 버린다 그만큼 k를 줄인다.
        Set<Integer> notExistNumbers = new HashSet<>();
        int position = 0;
        while (sb.length() != resultLength) {
            if (k == 0) {
                sb.append(charArray[position++]);
                continue;
            }
            for (int i = 9; i >= 0; i--) {
                if (!notExistNumbers.contains(i)) {
                    int index = findIndex(charArray, position, (char) ('0' + i));
                    if (index == -1) {
                        notExistNumbers.add(i);
                        continue;
                    }
                    if (index - position <= k) {
                        k = k - (index - position);
                        position = index + 1;
                        sb.append(i);
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    int findIndex(char[] numbers, int start, char target) {
        for (int i = start; i < numbers.length; i++) {
            if (numbers[i] == (target))
                return i;
        }
        return -1;
    }
}
