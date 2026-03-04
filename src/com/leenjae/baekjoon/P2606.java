package com.leenjae.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 백준 - 실버3
 * 바이러스
 *
 * @author leenjae96
 * @date 25.12.12
 */
public class P2606 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    void solution() throws IOException {
        int computerNumber = Integer.parseInt(br.readLine());
        int networkNumber = Integer.parseInt(br.readLine());

        List<List<Integer>> networks = new ArrayList<List<Integer>>();
        for(int i = 0 ; i <= computerNumber ; i++) {
            networks.add(new ArrayList<>());
        }
        for (int i = 0; i < networkNumber; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            networks.get(c1).add(c2);
            networks.get(c2).add(c1);
        }

        boolean[] visited = new boolean[computerNumber + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] =true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            List<Integer> linkedComputers = networks.get(cur);
            for (Integer linkedComputer : linkedComputers) {
                if (!visited[linkedComputer]) {
                    visited[linkedComputer] = true;
                    queue.add(linkedComputer);
                }
            }
        }

        int answer = -1;
        for(boolean check : visited){
            if(check){
                answer++;
            }
        }
        System.out.println(answer);
    }
}
