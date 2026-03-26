package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 백준 - 골드4
 * 단어 수학
 * 그리디, 정렬
 *
 * @author leenjae96
 * @date 26.03.25
 */
public class P1339 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] weightArray = new int[500];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            char[] chars = word.toCharArray();
            int length = word.length();

            for (int j = 0; j < length; j++) {
                char alpha = chars[j];
                int weight = (int) Math.pow(10, length - j - 1);

                weightArray[alpha] += weight;
            }
        }
        Arrays.sort(weightArray);

        int answer = 0;
        int weight = 9;
        for (int i = weightArray.length - 1; i >= weightArray.length - 10; i--) {
            answer += weightArray[i] * weight;
            weight--;
        }

        System.out.println(answer);
    }

    void solution2() throws IOException {
        int N = Integer.parseInt(br.readLine());

        Map<Character, Integer> weightMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            char[] chars = word.toCharArray();
            int length = word.length();

            for (int j = 0; j < length; j++) {
                char alpha = chars[j];
                int weight = (int) Math.pow(10, length - j - 1);

                if (!weightMap.containsKey(alpha)) {
                    weightMap.put(alpha, weight);
                } else {
                    weightMap.put(alpha, weightMap.get(alpha) + weight);
                }
            }
        }

        PriorityQueue<Alpha> pq = new PriorityQueue<>((o1, o2) -> {
            return o2.weight - o1.weight;
        });
        weightMap.forEach((alpha, weight) -> {
            pq.offer(new Alpha(alpha, weight));
        });

        int answer = 0;
        int value = 9;
        while (!pq.isEmpty()) {
            answer += pq.poll().weight * value;
            value--;
        }

        System.out.println(answer);
    }
}
class Alpha {
    char c;
    int weight;

    public Alpha(char c, int weight) {
        this.c = c;
        this.weight = weight;
    }
}
