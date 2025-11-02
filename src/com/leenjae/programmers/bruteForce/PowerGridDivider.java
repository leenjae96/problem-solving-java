package com.leenjae.programmers.bruteForce;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 프로그래머스 - 완전탐색 - level2
 * 전력망 둘로 나누기
 *
 * @author leenjae96
 */
public class PowerGridDivider {
    // [n]번째 노드의 연결 정보를 넣기 위한 List 배열
    List<Integer>[] connections;

    public int solution(int n, int[][] wires) {
        // 최대값으로 init.
        int answer = Integer.MAX_VALUE;

        // connections ArratList<>로 init.
        connections = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            connections[i] = new ArrayList<>();
        }

        // wires 정보를 가지고
        // 왼쪽 노드에는 오른쪽 노드를 list에 더해주고
        // 오른쪽 노드에는 왼쪽 노드를 list에 더해준다.
        for (int[] wire : wires) {
            int left = wire[0];
            int right = wire[1];

            connections[left].add(right);
            connections[right].add(left);
        }
        // 다시 순회하며 현재 연결을 끊었을 때 왼쪽, 오른쪽 연결 점들을 계산.
        // 계산은 bsf 알고리즘.
        for (int[] wire : wires) {
            int left = wire[0];
            int right = wire[1];
            // -- List.remove()에서 파라미터로 int i 를 주면 i번째 요소 삭제
            // -- Object o를 찾은 첫번째 o를 삭제.
            connections[left].remove(Integer.valueOf(right));
            connections[right].remove(Integer.valueOf(left));

            int left_cnt = bsf(left, n);
            int right_cnt = bsf(right, n);
            //연결 다시 붙여주기.
            connections[left].add(right);
            connections[right].add(left);

            //계산
            answer = Math.min(answer, Math.abs(left_cnt - right_cnt));
        }

        return answer;
    }

    //현재 시작점을 topNode로 생각하여 내려가며 계산.
    int bsf(Integer start, int n) {
        int cnt = 0;
        //n 노드 visited 체크를 위한 배열 init.
        boolean[] isVisited = new boolean[n + 1];
        //Queue 생성 TODO: 자료구조
        Queue<Integer> queue = new ArrayDeque<>();
        isVisited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for(int i = 0 ; i < connections[now].size() ; i++) {
                int next = connections[now].get(i);
                if(isVisited[next]) {
                    continue;
                }

                cnt++;
                isVisited[next] = true;
                queue.add(next);
            }
        }
        return cnt;
    }
}
