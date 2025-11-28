package com.leenjae.programmers.greedy;

import java.util.*;

/**
 * 프로그래머스 - 그리디 - level3
 * 섬 연결하기
 *
 * @author leenjae96
 * @date 25.11.28
 */
public class LinkingIsland {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        /*Arrays.sort(costs, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);*/
        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));
        HashSet<Integer> linkedIslands = new HashSet<>();
        List<int[]> linkInfoList = new ArrayList<>();
        for (int[] cost : costs) {
            if(!linkedIslands.contains(cost[0]) || !linkedIslands.contains(cost[1])) {
                answer += cost[2];
                int[] link = new int[]{cost[0], cost[1]};
                linkInfoList.add(link);
            }
            linkedIslands = getLinkedIslands(linkInfoList);
            if (linkedIslands.size() == n) {
                break;
            }
        }

        return answer;
    }

    HashSet<Integer> getLinkedIslands(List<int[]> linkList) {
        int start = linkList.get(0)[0];

        HashSet<Integer> linkedIslands = new HashSet<>();
        boolean[] visited = new boolean[linkList.size()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer island = queue.poll();
            for (int i = 0; i < linkList.size(); i++) {
                if (visited[i]) {
                    continue;
                }
                int[] link = linkList.get(i);
                if (link[0] == island || link[1] == island) {
                    linkedIslands.add(link[0]);
                    linkedIslands.add(link[1]);
                    queue.add(link[0] == island ? link[1] : link[0]);
                    visited[i] = true;
                }
            }
        }
        return linkedIslands;
    }
}
