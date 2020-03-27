package com.dotterbear.iteratoration.steam.benchmark;

import org.openjdk.jmh.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(IteratorationSteamBenchmark.N)
public class IteratorationSteamBenchmark {

  public static final int N = 10000;

    static List<Integer> numsArrayList = new ArrayList<>();
    static {
        for (int i = 0; i < N; i++) {
            numsArrayList.add(i);
        }
    }
  
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public List<Double> for1() {
      List<Double> results = new ArrayList<>(N / 2 + 1);
      for (int i = 0; i < N; i++) {
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> for2() {
      List<Double> results = new ArrayList<>(numsArrayList.size() / 2 + 1);
      for (int i = 0; i < numsArrayList.size(); i++) {
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> for3() {
      List<Double> results = new ArrayList<>(numsArrayList.size() / 2 + 1);
      for (int i : numsArrayList) {
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

}
