package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 - 신입 사원- 실버1(그리디)
 *
 * @author leenjae96
 * @date 26.03.25
 */
public class P1946 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    void solution() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int C = Integer.parseInt(br.readLine());

            int[] resume = new int[C+1];
            for (int j = 0; j < C; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int paper = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());
                resume[paper] = interview;
            }
            int count = 1;
            int standard = resume[1];
            for(int j = 2 ; j <= C ; j++) {
                if (resume[j] > standard) {
                    continue;
                }
                standard = resume[j];
                count++;
                if(standard == 1) break;
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb.toString());
    }
}
