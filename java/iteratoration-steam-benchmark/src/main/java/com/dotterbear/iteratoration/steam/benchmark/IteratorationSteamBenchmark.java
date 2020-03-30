package com.dotterbear.iteratoration.steam.benchmark;

import org.openjdk.jmh.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.results.format.ResultFormatType;
import java.util.stream.Collectors;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(IteratorationSteamBenchmark.N)
@Fork(2)
@Warmup(iterations = 2, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 2, time = 500, timeUnit = TimeUnit.MILLISECONDS)
public class IteratorationSteamBenchmark {

  public static final int N = 1000;

    static List<Integer> numsArrayList = new ArrayList<>();
    static {
        for (int i = 0; i < N; i++) {
            numsArrayList.add(i);
        }
    }
  
    public static void main(String[] args) throws RunnerException {
      System.out.println("log 1");
      Options opt = new OptionsBuilder()
        .include(IteratorationSteamBenchmark.class.getSimpleName())
        .result("jmh-report.txt")
        .resultFormat(ResultFormatType.JSON)
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
      return numsArrayList.stream()
        .filter(num -> num % 2 == 0)
        .map(Math::sqrt)
        .collect(Collectors.toCollection(() -> new ArrayList<>(numsArrayList.size() / 2 + 1)));
    }

    @Benchmark
    public List<Double> iteratorFor() {
      List<Double> results = new ArrayList<>(numsArrayList.size() / 2 + 1);
      for (Iterator<Integer> iter = numsArrayList.iterator(); iter.hasNext();) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> iteratorWhile() {
      List<Double> results = new ArrayList<>(numsArrayList.size() / 2 + 1);
      Iterator<Integer> iter = numsArrayList.iterator();
      while (iter.hasNext()) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }
}
