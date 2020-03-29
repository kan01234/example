package com.dotterbear.iteratoration.steam.benchmark;

import org.openjdk.jmh.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(IteratorationSteamBenchmark.N)
public class IteratorationSteamBenchmark {

  public static final int N = 1000;

    static List<Integer> numsArrayList = new ArrayList<>();
    static {
        for (int i = 0; i < N; i++) {
            numsArrayList.add(i);
        }
    }
  
    public static void main(String[] args) throws RunnerException {
      Options opt = new OptionsBuilder()
        .include(IteratorationSteamBenchmark.class.getSimpleName())
        .forks(1)
        .warmupIterations(2)
        .measurementIterations(2)
        .build();
        new Runner(opt).run();
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

    @Benchmark
    public List<Double> steam1() {
      List<Double> results = new ArrayList<>(numsArrayList.size() / 2 + 1);
      // TODO
      return results;
    }

}
