package com.dotterbear.iteratoration.steam.benchmark;

import org.openjdk.jmh.annotations.*;
import java.util.ArrayList;
import java.util.Collection;
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
    static List<Integer> numsLinkedList = new LinkedList<>();
    static {
        for (int i = 0; i < N; i++) {
            numsArrayList.add(i);
            numsLinkedList.add(i);
        }
    }
  
//     public static void main(String[] args) throws RunnerException {
//       System.out.println("log 1");
//       Options opt = new OptionsBuilder()
//         .include(IteratorationSteamBenchmark.class.getSimpleName())
//         .result("jmh-report.txt")
//         .resultFormat(ResultFormatType.JSON)
//         .build();
//         new Runner(opt).run();
//     }

    @Benchmark
    public List<Double> for1ArrayList() {
      List<Double> results = build(numsArrayList);
      for (int i = 0; i < N; i++) {
        int num = numsArrayList.get(i);
        if (num % 2 == 0)
          results.add(Math.sqrt(num));
      }
      return results;
    }

    @Benchmark
    public List<Double> for2ArrayList() {
      List<Double> results = build(numsArrayList);
      for (int i = 0; i < numsArrayList.size(); i++) {
        int num = numsArrayList.get(i);
        if (i % num == 0)
          results.add(Math.sqrt(num));
      }
      return results;
    }

    @Benchmark
    public List<Double> for3ArrayList() {
      List<Double> results = build(numsArrayList);
      for (int i : numsArrayList) {
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> steam1ArrayList() {
      return numsArrayList.stream()
        .filter(num -> num % 2 == 0)
        .map(Math::sqrt)
        .collect(Collectors.toCollection(() -> build(numsArrayList)));
    }

    @Benchmark
    public List<Double> iteratorForArrayList() {
      List<Double> results = build(numsArrayList);
      for (Iterator<Integer> iter = numsArrayList.iterator(); iter.hasNext();) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> iteratorWhileArrayList() {
      List<Double> results = build(numsArrayList);
      Iterator<Integer> iter = numsArrayList.iterator();
      while (iter.hasNext()) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> listIteratorForArrayList() {
      List<Double> results = build(numsArrayList);
      for(ListIterator<Integer> iter = numsArrayList.listIterator(); iter.hasNext();) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }
  
    @Benchmark
    public List<Double> listIteratorWhileArrayList() {
      List<Double> results = build(numsArrayList);
      ListIterator<Integer> iter = numsArrayList.listIterator();
      while (iter.hasNext()) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }
                 
    // linked list
    @Benchmark
    public List<Double> for1LinkedList() {
      List<Double> results = build(numsLinkedList);
      for (int i = 0; i < N; i++) {
        int num = numsLinkedList.get(i);
        if (num % 2 == 0)
          results.add(Math.sqrt(num));
      }
      return results;
    }

    @Benchmark
    public List<Double> for2LinkedList() {
      List<Double> results = build(numsLinkedList);
      for (int i = 0; i < numsLinkedList.size(); i++) {
        int num = numsLinkedList.get(i);
        if (i % num == 0)
          results.add(Math.sqrt(num));
      }
      return results;
    }

    @Benchmark
    public List<Double> for3LinkedList() {
      List<Double> results = build(numsLinkedList);
      for (int i : numsLinkedList) {
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> steam1LinkedList() {
      return numsLinkedList.stream()
        .filter(num -> num % 2 == 0)
        .map(Math::sqrt)
        .collect(Collectors.toCollection(() -> build(numsLinkedList)));
    }

    @Benchmark
    public List<Double> iteratorForLinkedList() {
      List<Double> results = build(numsLinkedList);
      for (Iterator<Integer> iter = numsLinkedList.iterator(); iter.hasNext();) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> iteratorWhileLinkedList() {
      List<Double> results = build(numsLinkedList);
      Iterator<Integer> iter = numsLinkedList.iterator();
      while (iter.hasNext()) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    @Benchmark
    public List<Double> listIteratorForLinkedList() {
      List<Double> results = build(numsLinkedList);
      for(ListIterator<Integer> iter = numsLinkedList.listIterator(); iter.hasNext();) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }
  
    @Benchmark
    public List<Double> listIteratorWhileLinkedList() {
      List<Double> results = build(numsLinkedList);
      ListIterator<Integer> iter = numsLinkedList.listIterator();
      while (iter.hasNext()) {
        Integer i = iter.next();
        if (i % 2 == 0)
          results.add(Math.sqrt(i));
      }
      return results;
    }

    private Collection<Double> build(Collection<Integer> collection) {
      switch (collection.getClass().getSimpleName()) {
        case "ArrayList":
          return new ArrayList<>(collection.size() / 2 + 1);
        case "LinkedList":
          return new LinkedList();
        default:
          return Collections.emptyList();
      }
    }
  
}
