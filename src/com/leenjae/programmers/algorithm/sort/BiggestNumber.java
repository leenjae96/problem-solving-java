package com.leenjae.programmers.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 프로그래머스 - 해시 - level2
 * 가장 큰 수
 *
 * @author leenjae96
 */
public class BiggestNumber {
    public String solution(int[] numbers) {
        List<String> numberStrings = new ArrayList<>();
        for (int number : numbers) {
            numberStrings.add(String.valueOf(number));
        }
        numberStrings.sort((o1, o2) -> {
            int i1 = Integer.parseInt(o1 + o2);
            int i2 = Integer.parseInt(o2 + o1);
            return i2 - i1;
        });
        if (numberStrings.get(0).equals("0")) {
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        for (String number : numberStrings) {
            builder.append(number);
        }
        return builder.toString();
    }
}
