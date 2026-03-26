package com.leenjae.programmers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        List<Integer> rockList = Arrays.stream(rocks)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
        rockList.add(distance);

        int front = 1;
        int rear = distance;
        while (front <= rear) {
            int mid = (front + rear) / 2; //최소거리 후보
            //최소거리 후보 보다 작으면 돌 삭제.
            int count = 0;
            int lastPosition = 0;
            for (int i = 0; i < rockList.size() && count <= n ; i++) {
                if (rockList.get(i) - lastPosition < mid) {
                    count ++;
                    continue;
                }
                lastPosition = rockList.get(i);
            }

            if(count > n) {
                rear = mid - 1;
            } else {
                front = mid + 1;
                answer = mid;
            }
        }
        return answer;
    }
}

public class MainForProgrammers {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                solution.solution(25, new int[]{2, 14, 11, 21, 17}, 2)
        );
    }
}