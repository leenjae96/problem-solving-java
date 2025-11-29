package com.leenjae.programmers.algorithm.heap;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


/**
 * 프로그래머스 - heap - level3
 * 디스크 컨트롤러
 *
 * @author leenjae96
 * @date 25.11.24
 */
public class DiskController {
    int INDEX;

    public int solution(int[][] jobs) {
        int answer = 0;
        List<JobInfo> jobList = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            jobList.add(new JobInfo(jobs[i][0], jobs[i][1], i));
        }
        jobList = jobList.stream()
                .sorted(Comparator.comparingInt(o -> o.requestTime))
                .collect(Collectors.toList());


        int time = 0;
        PriorityQueue<JobInfo> pq = new PriorityQueue<>();
        while (INDEX < jobList.size() || !pq.isEmpty()) {
            time = addToTaskQueue(jobList, pq, time);
            JobInfo jobInfo = pq.poll();
            time += jobInfo.requireTime;
            answer = answer + (time - jobInfo.requestTime);
        }

        return answer / jobList.size();
    }

    int addToTaskQueue(List<JobInfo> jobList, PriorityQueue<JobInfo> pq, int time) {
        while (INDEX < jobList.size()) {
            JobInfo job = jobList.get(INDEX);
            if (time < job.requestTime) {
                if (!pq.isEmpty()) {
                    break;
                }
                time = job.requestTime;
            }
            pq.add(job);
            INDEX++;
        }
        return time;
    }

}

class JobInfo implements Comparable<JobInfo> {
    int requestTime;
    int requireTime;
    int number;

    JobInfo(int requestTime, int requireTime, int number) {
        this.requestTime = requestTime;
        this.requireTime = requireTime;
        this.number = number;
    }

    @Override
    public int compareTo(JobInfo o) {
        if (requireTime > o.requireTime) {
            return 1;
        } else if (requireTime == o.requireTime) {
            if (requestTime > o.requestTime) {
                return 1;
            } else if (requestTime == o.requestTime) {
                if (number > o.number) {
                    return 1;
                } else if (number == o.number) {
                    return 0;
                }
            }
        }
        return -1;
    }
}