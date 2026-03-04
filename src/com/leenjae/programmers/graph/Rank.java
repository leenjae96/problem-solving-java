package com.leenjae.programmers.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 프로그래머스 - 그래프 - level3
 * 순위
 *
 * @author leenjae96
 * @date 25.12.12
 */
public class Rank {
    HashMap<Integer, Player> playerMap = new HashMap<>();

    public int solution(int n, int[][] results) {
        for (int i = 1; i <= n; i++) {
            playerMap.put(i, new Player());
        }

        for (int[] result : results) {
            Player winner = playerMap.get(result[0]);
            Player loser = playerMap.get(result[1]);

            winner.lowers.add(loser);
            loser.highers.add(winner);
        }

        for (Player player : playerMap.values()) {
            HashSet<Player> highers = player.highers;
            HashSet<Player> lowers = player.lowers;

            for (Player winner : highers) {
                winner.lowers.addAll(lowers);
            }

            for (Player loser : lowers) {
                loser.highers.addAll(highers);
            }
        }

        int answer = 0;
        for (Player player : playerMap.values()) {
            if (player.highers.size() + player.lowers.size() == n - 1) {
                answer++;
            }
        }
        return answer;
    }
}

class Player {
    HashSet<Player> highers = new HashSet<>();
    HashSet<Player> lowers = new HashSet<>();
}