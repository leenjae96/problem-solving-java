package com.leenjae.programmers.algorithm.basic;

/**
 * 프로그래머스 - (기본) - level1
 * 최대공약수와 최소공배수
 *
 * @author leenjae96
 * @date 25.11.15
 */
public class GcdLcm {
    //최대공약수를 구하는 공식은
    public int[] solution(int n, int m) {
        int gcd = gcd(Math.max(n, m), Math.min(n, m));
        int lcm = n * m / gcd(Math.max(n, m), Math.min(n, m));

        return new int[]{gcd, lcm};
    }

    int gcd(int a, int b) {
        if (b % a == 0) return a;
        return gcd(b, a % b);
    }
}
