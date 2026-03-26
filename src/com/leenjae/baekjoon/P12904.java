package com.leenjae.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준- A와 B- 골드5(그리디)
 *
 * @author leenjae96
 * @date 26.03.25
 */
public class P12904 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    void solution() throws IOException {
        String S = br.readLine();
        String T = br.readLine();

        int targetLength = S.length();
        sb = new StringBuilder(T);
        while (sb.length() > targetLength) {
            if (sb.toString().endsWith("B")) {
                sb.delete(sb.length() - 1, sb.length());
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < sb.length(); i++) {
                    temp.append(sb.charAt(sb.length() - i - 1));
                }
                sb.reverse();
                sb = temp;
            } else {
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        if (sb.toString().equals(S)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
