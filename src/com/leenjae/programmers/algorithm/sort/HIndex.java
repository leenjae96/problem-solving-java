package com.leenjae.programmers.algorithm.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 프로그래머스 - 해시 - level2
 * H-Index
 *
 * @author leenjae96
 */
public class HIndex {
    public int solution(int[] citations) {
        int answer = 0;

        List<Integer> citationList = Arrays.stream(citations).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());

        for (int hIndex = citations.length; hIndex > 0; hIndex--) {
            int temp = citationList.get(hIndex - 1);
            if (temp >= hIndex) {
                answer = hIndex;
                break;
            }
        }

        return answer;
    }
}
