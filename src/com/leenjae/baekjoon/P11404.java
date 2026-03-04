package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 - 골드4
 * 플로이드
 *
 * @author leenjae96
 * @date 25.12.13
 */
public class P11404 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    void solution() throws IOException {
        int cityNumber = Integer.parseInt(br.readLine());
        int busNumber = Integer.parseInt(br.readLine());

        int[][] costMap = new int[cityNumber + 1][cityNumber + 1];
        for (int i = 0; i < busNumber; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            costMap[from][to] = costMap[from][to] == 0 ?
                    cost : Math.min(cost, costMap[from][to]);
        }

        for (int k = 1; k <= cityNumber; k++) {
            for (int i = 1; i <= cityNumber; i++) {
                for (int j = 1; j <= cityNumber; j++) {
                    if (costMap[i][k] == 0 || costMap[k][j] == 0) {
                        continue;
                    }

                    if (costMap[i][j] == 0) {
                        costMap[i][j] = costMap[i][k] + costMap[k][j];
                    } else {
                        costMap[i][j] = Math.min(costMap[i][j], costMap[i][k] + costMap[k][j]);
                    }
                }
            }
        }

        for (int i = 1; i <= cityNumber; i++) {
            for (int j = 1; j <= cityNumber; j++) {
                if (i == j) sb.append(0);
                else sb.append(costMap[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
