package com.leenjae.programmers.stackQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 -스택/큐 - level2
 * 다리를 지나는 트럭
 *
 * @author leenjae96
 * @date 25.11.28
 */
public class TruckCrossingBridge {
    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        int answer = 0;
        //waiting 중인 차들을 담을 수 있는 queue.
        Queue<Truck> waiting = new LinkedList<>();
        for (int w : truckWeights) {
            waiting.add(new Truck(bridgeLength, w));
        }
        //passing 중인 차들을 담을 수 있는 queue.
        Queue<Truck> passing = new LinkedList<>();
        //현재 다리 위 무게 onBridge
        int onBridge = 0;
        //다리를 지나고 있는 차가 있거나 대기중인 차가 있을 때 까지만 수행
        while (!passing.isEmpty() || !waiting.isEmpty()) {
            if (!passing.isEmpty()) {
                //지나는 차들에 대해 수행.
                int truckCountOnBridge = passing.size();
                for (int j = 0; j < truckCountOnBridge; j++) {
                    Truck truck = passing.poll();
                    if (truck.remaining == 1) {
                        onBridge -= truck.weight;
                    } else {
                        truck.remaining--;
                        passing.add(truck);
                    }
                }
            }
            if (!waiting.isEmpty()) {
                //기다리는 차들 중 수행.
                Truck nextTruck = waiting.peek();
                if (passing.size() < bridgeLength && nextTruck.weight + onBridge <= weight) {
                    nextTruck = waiting.poll();
                    passing.add(nextTruck);
                    onBridge = nextTruck.weight + onBridge;
                }
            }
            //한 사이클 지나면 무조건 +1
            answer++;
        }
        return answer;
    }
}

class Truck {
    int remaining;
    int weight;

    Truck(int remaining, int weight) {
        this.remaining = remaining;
        this.weight = weight;
    }
}

