package com.leenjae.programmers.bruteForce;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 프로그래머스 - 완전탐색 - level1
 * 모의고사
 *
 * @author leenjae96
 */
public class MockExam {
    public int[] solution(int[] answers) {
        //수포자들이 적은 답안지를 먼저 초기화 해둡니다.
        int[] supoMan1 = {1, 2, 3, 4, 5};
        int[] supoMan2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] supoMan3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        //각 수포자들의 점수도 초기화 해둡니다.
        int[] scores = {0, 0, 0};
        for (int i = 0; i < answers.length; i++) {
            //전체를 순회하며 맞은 문제는 점수 +1
            int answer = answers[i];
            if (answer == supoMan1[i % 5]) {
                scores[0]++;
            }
            if (answer == supoMan2[i % 8]) {
                scores[1]++;
            }
            if (answer == supoMan3[i % 10]) {
                scores[2]++;
            }
        }
        //-- Arrays.stream().max().getAsInt()로 최대 값을 얻습니다.
        int maxScore = Arrays.stream(scores)
                .max()
                .getAsInt();
        //-- IntStream.range(a,b)로 a <= ~ < b 인 IntSteam을 만듭니다.
        return IntStream.range(0, scores.length)
                .filter(i -> scores[i] == maxScore) //순서대로 얻은 값 i에 대해 조건문
                .map(i -> i + 1) //i에 대해 +1 하여 값을 저장.
                .toArray(); // 저장한 것들을 배열로 return
    }
}
