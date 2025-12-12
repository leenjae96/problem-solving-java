package com.leenjae.baekjoon;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 백준 - 실버3
 * N과 M(1)
 *
 * @author leenjae96
 * @date 25.12.02
 */
public class P15649 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<Integer> visited = new HashSet<>();

        permutation(n, m, 0, visited, new int[m]);

        bw.write(sb.toString());
        bw.flush();
    }

    void permutation(int n, int m, int depth, HashSet<Integer> visited, int[] temp) throws IOException {
        if (depth == m) {
            for (int i : temp) {
                sb.append(i);
                sb.append(" ");
            }
            sb.append('\n');
        } else {
            for (int i = 1; i <= n; i++) {
                if (!visited.contains(i)) {
                    visited.add(i);
                    temp[depth] = i;
                    permutation(n, m, depth + 1, visited, temp);
                    visited.remove(i);
                }
            }
        }
    }
}
