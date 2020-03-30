package com.dotterbear.iteratoration.steam.benchmark;

import org.openjdk.jmh.annotations.*;
import java.util.*;
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

  public static final int N = 500;

  private static List<Integer> numsArrayList = new ArrayList<>();
  private static LinkedList<Integer> numsLinkedList = new LinkedList<>();
  private static List<Integer> numsVector = new Vector<>();
  private static Stack<Integer> numsStack = new Stack<>();
  
  static {
    for (int i = 0; i < N; i++) {
      numsArrayList.add(i);
      numsLinkedList.add(i);
      numsVector.add(i);
    }
    for (int i = N - 1; i >= 0; i++) {
      numsStack.push(i);
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
  public Collection<Double> for1ArrayList() {
    Collection<Double> results = build(numsArrayList);
    for (int i = 0; i < N; i++) {
      int num = numsArrayList.get(i);
      if (num % 2 == 0)
        results.add(Math.sqrt(num));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> for2ArrayList() {
    Collection<Double> results = build(numsArrayList);
    for (int i = 0; i < numsArrayList.size(); i++) {
      int num = numsArrayList.get(i);
      if (i % 2 == 0)
        results.add(Math.sqrt(num));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> for3ArrayList() {
    Collection<Double> results = build(numsArrayList);
    for (int i : numsArrayList) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> steam1ArrayList() {
    return numsArrayList.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toCollection(() -> build(numsArrayList)));
  }

  @Benchmark
  public Collection<Double> iteratorForArrayList() {
    Collection<Double> results = build(numsArrayList);
    for (Iterator<Integer> iter = numsArrayList.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> iteratorWhileArrayList() {
    Collection<Double> results = build(numsArrayList);
    Iterator<Integer> iter = numsArrayList.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> listIteratorForArrayList() {
    Collection<Double> results = build(numsArrayList);
    for(ListIterator<Integer> iter = numsArrayList.listIterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> listIteratorWhileArrayList() {
    Collection<Double> results = build(numsArrayList);
    ListIterator<Integer> iter = numsArrayList.listIterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  // linked list
//     @Benchmark
//     public Collection<Double> for1LinkedList() {
//       Collection<Double> results = build(numsLinkedList);
//       for (int i = 0; i < N; i++) {
//         int num = numsLinkedList.get(i);
//         if (num % 2 == 0)
//           results.add(Math.sqrt(num));
//       }
//       return results;
//     }

//     @Benchmark
//     public Collection<Double> for2LinkedList() {
//       Collection<Double> results = build(numsLinkedList);
//       for (int i = 0; i < numsLinkedList.size(); i++) {
//         int num = numsLinkedList.get(i);
//         if (i % 2 == 0)
//           results.add(Math.sqrt(num));
//       }
//       return results;
//     }

  @Benchmark
  public Collection<Double> for3LinkedList() {
    Collection<Double> results = build(numsLinkedList);
    for (int i : numsLinkedList) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> steam1LinkedList() {
    return numsLinkedList.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toCollection(() -> build(numsLinkedList)));
  }

  @Benchmark
  public Collection<Double> iteratorForLinkedList() {
    Collection<Double> results = build(numsLinkedList);
    for (Iterator<Integer> iter = numsLinkedList.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> iteratorWhileLinkedList() {
    Collection<Double> results = build(numsLinkedList);
    Iterator<Integer> iter = numsLinkedList.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> listIteratorForLinkedList() {
    Collection<Double> results = build(numsLinkedList);
    for(ListIterator<Integer> iter = numsLinkedList.listIterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> listIteratorWhileLinkedList() {
    Collection<Double> results = build(numsLinkedList);
    ListIterator<Integer> iter = numsLinkedList.listIterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> zwhileLinkedListPoll() {
    Collection<Double> results = build(numsLinkedList);
    while(!numsLinkedList.isEmpty()) {
      int i = numsLinkedList.poll();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  // vector
  @Benchmark
  public Collection<Double> for1Vector() {
    Collection<Double> results = build(numsVector);
    for (int i = 0; i < N; i++) {
      int num = numsVector.get(i);
      if (num % 2 == 0)
        results.add(Math.sqrt(num));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> for2Vector() {
    Collection<Double> results = build(numsVector);
    for (int i = 0; i < numsVector.size(); i++) {
      int num = numsVector.get(i);
      if (i % 2 == 0)
        results.add(Math.sqrt(num));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> for3Vector() {
    Collection<Double> results = build(numsVector);
    for (int i : numsVector) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> steam1numsVector() {
    return numsVector.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toCollection(() -> build(numsVector)));
  }

  @Benchmark
  public Collection<Double> iteratorFornumsVector() {
    Collection<Double> results = build(numsVector);
    for (Iterator<Integer> iter = numsVector.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> iteratorWhileVector() {
    Collection<Double> results = build(numsVector);
    Iterator<Integer> iter = numsVector.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> listIteratorForVector() {
    Collection<Double> results = build(numsVector);
    for(ListIterator<Integer> iter = numsVector.listIterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  @Benchmark
  public Collection<Double> listIteratorWhileVector() {
    Collection<Double> results = build(numsVector);
    ListIterator<Integer> iter = numsVector.listIterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  // stack
  @Benchmark
  public Collection<Double> zwhileStackPop() {
    Collection<Double> results = build(numsStack);
    while(!numsStack.empty()) {
      int i = numsStack.pop();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    return results;
  }

  private Collection<Double> build(Collection<Integer> collection) {
    int size = collection.size() / 2 + 1;
    switch (collection.getClass().getSimpleName()) {
      case "ArrayList":
        return new ArrayList<>(size);
      case "LinkedList":
        return new LinkedList();
      case "Vector":
        return new Vector(size);
      case "Stack":
        return new Stack();
      default:
        return Collections.emptyList();
    }
  }
  
}
