package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 - 실버1
 * 연산자 끼워넣기
 *
 * @author leenjae96
 * @date 25.12.06
 */
public class P14888 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    int[] numbers;
    int[] symbols;
    boolean[] visited;
    List<int[]> symbolOrderList;

    void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        symbols = new int[n - 1];

        int idx = 0;
        for (int i = 0; i < 4; i++) {
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                symbols[idx++] = i;
            }
        }

        visited = new boolean[n];
        symbolOrderList = new ArrayList<>();
        permutate(0, new int[n - 1]);

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int[] symbolOrder : symbolOrderList) {
            int value = numbers[0];
            for (int i = 0; i < symbolOrder.length; i++) {
                int symbol = symbolOrder[i];
                switch (symbol) {
                    case 0:
                        value += numbers[i + 1];
                        break;
                    case 1:
                        value -= numbers[i + 1];
                        break;
                    case 2:
                        value *= numbers[i + 1];
                        break;
                    case 3:
                        value /= numbers[i + 1];
                }
            }
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        System.out.println(max);
        System.out.println(min);
    }

    void permutate(int depth, int[] result) {
        if (depth == result.length) {
            symbolOrderList.add(result.clone());
        } else {
            for (int i = 0; i < symbols.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    result[depth] = symbols[i];
                    permutate(depth + 1, result);
                    visited[i] = false;
                }
            }
        }
    }
}
