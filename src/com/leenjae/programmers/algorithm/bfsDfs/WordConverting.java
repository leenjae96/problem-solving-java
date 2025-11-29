package com.leenjae.programmers.algorithm.bfsDfs;

import java.util.*;

/**
 * 프로그래머스 - BFS/DFS - level3
 * 단어 변환
 *
 * @author leenjae96
 * @date 25.11.15
 */
public class WordConverting {
    int LEGNTH;
    Set<String> WORDSET;
    Queue<WordNode> QUEUE;

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        LEGNTH = begin.length();
        WORDSET = new HashSet<>(Arrays.asList(words));
        if (!WORDSET.contains(target)) {
            return 0;
        }
        QUEUE = new LinkedList<>();

        WordNode beginNode = new WordNode(begin, 0);
        checkAndAddToQueue(beginNode);
        while (!QUEUE.isEmpty()) {
            WordNode node = QUEUE.poll();
            if (node.value.equals(target)) {
                answer = node.depth;
                break;
            }
            checkAndAddToQueue(node);
        }

        return answer;
    }

    void checkAndAddToQueue(WordNode beginNode) {
        for (String next : WORDSET) {
            if (reachable(beginNode.value, next)) {
                QUEUE.add(new WordNode(next, beginNode.depth + 1));
            }
        }
    }

    boolean reachable(String word, String next) {
        boolean check = false;
        for (int i = 0; i < LEGNTH; i++) {
            if (word.charAt(i) != next.charAt(i)) {
                if (check) return false;
                check = true;
            }
        }
        return true;
    }
}

class WordNode {
    String value;
    int depth;

    public WordNode(String value, int depth) {
        this.value = value;
        this.depth = depth;
    }
}