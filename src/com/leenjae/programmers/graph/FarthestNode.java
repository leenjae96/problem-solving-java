package com.leenjae.programmers.graph;

import java.util.*;

/**
 * 프로그래머스 - 그래프 - level3
 * 가장 먼 노드
 *
 * @author leenjae96
 * @date 25.12.12
 */
public class FarthestNode {
    HashMap<Integer, List<Integer>> graphMap = new HashMap<>();
    HashMap<Integer, Integer> distanceMap = new HashMap<>();

    public int solution(int n, int[][] edge) {
        int answer = 0;
        for (int[] e : edge) {
            int left = e[0], right = e[1];
            List<Integer> leftList = graphMap.computeIfAbsent(left, k -> new ArrayList<>());
            leftList.add(right);
            List<Integer> rightList = graphMap.computeIfAbsent(right, k -> new ArrayList<>());
            rightList.add(left);

            distanceMap.put(left, Integer.MAX_VALUE);
            distanceMap.put(right, Integer.MAX_VALUE);
        }
        distanceMap.put(1, 0);

        Queue<Integer> queue = new LinkedList<>();
        for (Integer i : graphMap.get(1)) {
            queue.add(i);
            distanceMap.put(i, 1);
        }
        int maxDistance = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> linked = graphMap.get(curr);
            for (Integer i : linked) {
                if (distanceMap.get(i) == Integer.MAX_VALUE) {
                    distanceMap.put(i, distanceMap.get(curr) + 1);
                    queue.add(i);
                } else {
                    distanceMap.put(i, Math.min(distanceMap.get(i), distanceMap.get(curr) + 1));
                }
            }
            maxDistance = Math.max(maxDistance, distanceMap.get(curr));
        }

        for (Integer i : distanceMap.keySet()) {
            if (distanceMap.get(i) == maxDistance) {
                answer++;
            }
        }

        return answer;
    }
}
