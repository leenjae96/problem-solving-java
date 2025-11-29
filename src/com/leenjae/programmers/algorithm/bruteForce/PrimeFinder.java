package com.leenjae.programmers.algorithm.bruteForce;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 프로그래머스 - 완전탐색 - level2
 * 소수 찾기
 *
 * @author leenjae96
 */
public class PrimeFinder {
    public int solution(String numbers) {
        //numbers를 List<String> papers 로 쪼개기
        List<String> papers = Arrays.stream(numbers.split(""))
                .collect(Collectors.toList());

        //재귀 함수로 길이 1부터 papers 길이 만큼 합친 숫자 조합 만들기
        HashSet<Integer> combinedNumbers = new HashSet<>();
        for (int length = 1; length <= papers.size(); length++) {
            combine(length, papers)
                    .stream()
                    //Stream<String> -> Stream<Integer>
                    .map(Integer::parseInt)
                    //값이 1보다 큰 것만 받기
                    .filter(i -> i > 1)
                    //Set에 넣음으로 중복 제거
                    .forEach(combinedNumbers::add);
        }
        // -- 만약 1보다 큰 값이 없으면 바로 리턴.
        if (combinedNumbers.isEmpty()) return 0;
        //조합수중 최대값 찾기
        int max = combinedNumbers.stream()
                .max(Integer::compareTo)
                .get();
        //최대값 기준으로 primeSet 초기화.
        boolean[] primeSet = initPrimeSet(max);
        //조합수 중 primeSet에 있는 것들만 count 해서 리턴.
        return (int) combinedNumbers.stream()
                .filter(number -> primeSet[number])
                .count();
    }

    List<String> combine(int length, List<String> papers) {
        if (length == 0) { //길이가 0이면 빈 리스트
            return Collections.emptyList();
        } else if (length == 1) { //길이가 1이면 받은 리스트를 그대로 return
            return new ArrayList<>(papers);
        } else { //길이가 1보다 크면 순회하면서 1개씩뽑고 남은 리스트를 다시 combine(length-1, copy)
            List<String> combinedList = new ArrayList<>();
            for (int i = 0; i < papers.size(); i++) {
                List<String> copy = new ArrayList<>(papers);
                String prefix = copy.get(i);
                copy.remove(i);
                combinedList.addAll(combine(length - 1, copy)
                        .stream()
                        .map(number -> prefix + number)
                        .collect(Collectors.toList())
                );
            }
            return combinedList;
        }
    }

    //에라스토테네스의 체 알고리즘 사용.
    boolean[] initPrimeSet(int max) {
        //최대값을 기준으로 primeSet생성 및 우선 true로 초기화.
        boolean[] primeSet = new boolean[max + 1];
        Arrays.fill(primeSet, true);
        //0 1 은 소수 아님.
        primeSet[0] = false;
        primeSet[1] = false;
        //2부터 max의 제곱근까지만 순회. <- 내부 for문에서 순회하는 초기 값이 제곱 값이므로.
        for (int i = 2; i <= Math.sqrt(max); i++) {
            //만약 현재 값이 소수라면
            if (primeSet[i]) {
                //그 값의 배수들은 전부 제거(false)
                //...여기서 j = i * i 인 이유는 예를 들어 5의 경우 그보다 작은 배수(2,3,4의 배수)는 이미 지웠으므로
                for (int j = i * i; j <= max; j += i) { // j += i 인 이유는 다음 배수를 찾기 위함
                    primeSet[j] = false;
                }
            }
        }
        return primeSet;
    }
}
