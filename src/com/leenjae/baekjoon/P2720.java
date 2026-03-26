package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 - 브론즈3(그리디)
 * 세탁소 사장 동혁
 *
 * @author leenjae96
 * @date 26.03.25
 */
public class P2720 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    void solution() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < T ; i++) {
            int C = Integer.parseInt(br.readLine());
            sb.append(C/25).append(" ");
            C = C % 25;
            sb.append(C/10).append(" ");
            C = C % 10;
            sb.append(C/5).append(" ");
            C = C % 5;
            sb.append(C).append("\n");
        }

        System.out.print(sb.toString());
    }
}
