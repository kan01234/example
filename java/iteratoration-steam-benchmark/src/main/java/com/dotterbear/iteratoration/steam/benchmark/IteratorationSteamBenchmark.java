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
import java.lang.RuntimeException;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 2, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 2, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@OperationsPerInvocation
public class IteratorationSteamBenchmark {

  private final int N = 100;
  private int expectedResultSize = -1;

  private List<Integer> numsArrayList = new ArrayList<>();
  private LinkedList<Integer> numsLinkedList = new LinkedList<>();
  private List<Integer> numsVector = new Vector<>();
  private Stack<Integer> numsStack = new Stack<>();
  private Set<Integer> numsHashSet = new HashSet<>();
  private Set<Integer> numsLinkedHashSet = new LinkedHashSet<>();
  private Set<Integer> numsTreeSet = new TreeSet<>();
  
  @Setup(Level.Trial)
  public void prepare() {
    expectedResultSize = 0;
    for (int i = 0; i < N; i++) {
      numsArrayList.add(i);
      numsLinkedList.add(i);
      numsVector.add(i);
      numsHashSet.add(i);
      numsLinkedHashSet.add(i);
      numsTreeSet.add(i);
      if (i % 2 == 0)
        expectedResultSize++;
    }
    for (int i = N - 1; i >= 0; i--) {
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
    List<Double> results = build();
    int size = numsArrayList.size();
    for (int i = 0; i < size; i++) {
      int num = numsArrayList.get(i);
      if (num % 2 == 0)
        results.add(Math.sqrt(num));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for2ArrayList() {
    List<Double> results = build();
    for (int i = 0; i < numsArrayList.size(); i++) {
      int num = numsArrayList.get(i);
      if (i % 2 == 0)
        results.add(Math.sqrt(num));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for3ArrayList() {
    List<Double> results = build();
    for (int i : numsArrayList) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> steam1ArrayList() {
    List<Double> results = build();
    results = numsArrayList.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toList());
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorForArrayList() {
    List<Double> results = build();
    for (Iterator<Integer> iter = numsArrayList.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorWhileArrayList() {
    List<Double> results = build();
    Iterator<Integer> iter = numsArrayList.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> listIteratorForArrayList() {
    List<Double> results = build();
    for(ListIterator<Integer> iter = numsArrayList.listIterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> listIteratorWhileArrayList() {
    List<Double> results = build();
    ListIterator<Integer> iter = numsArrayList.listIterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
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
  @OperationsPerInvocation(N)
  public Collection<Double> for3LinkedList() {
    List<Double> results = build();
    for (int i : numsLinkedList) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> steam1LinkedList() {
    List<Double> results = build();
    results = numsLinkedList.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toList());
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorForLinkedList() {
    List<Double> results = build();
    for (Iterator<Integer> iter = numsLinkedList.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorWhileLinkedList() {
    List<Double> results = build();
    Iterator<Integer> iter = numsLinkedList.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> listIteratorForLinkedList() {
    List<Double> results = build();
    for(ListIterator<Integer> iter = numsLinkedList.listIterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> listIteratorWhileLinkedList() {
    List<Double> results = build();
    ListIterator<Integer> iter = numsLinkedList.listIterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> whileLinkedListPoll() {
    List<Double> results = build();
    LinkedList<Integer> numsLinkedListCloned = (LinkedList<Integer>) numsLinkedList.clone();
    while(!numsLinkedListCloned.isEmpty()) {
      int i = numsLinkedListCloned.poll();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  // vector
  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for1Vector() {
    List<Double> results = build();
    int size = numsVector.size();
    for (int i = 0; i < N; i++) {
      int num = numsVector.get(i);
      if (num % 2 == 0)
        results.add(Math.sqrt(num));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for2Vector() {
    List<Double> results = build();
    for (int i = 0; i < numsVector.size(); i++) {
      int num = numsVector.get(i);
      if (i % 2 == 0)
        results.add(Math.sqrt(num));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for3Vector() {
    List<Double> results = build();
    for (int i : numsVector) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> steam1Vector() {
    return numsVector.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toCollection(() -> build()));
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorForVector() {
    List<Double> results = build();
    for (Iterator<Integer> iter = numsVector.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorWhileVector() {
    List<Double> results = build();
    Iterator<Integer> iter = numsVector.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> listIteratorForVector() {
    List<Double> results = build();
    for(ListIterator<Integer> iter = numsVector.listIterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> listIteratorWhileVector() {
    List<Double> results = build();
    ListIterator<Integer> iter = numsVector.listIterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  // stack
  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for1Stack() {
    List<Double> results = build();
    int size = numsStack.size();
    for (int i = 0; i < size; i++) {
      int num = numsStack.get(i);
      if (num % 2 == 0)
        results.add(Math.sqrt(num));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for2Stack() {
    List<Double> results = build();
    for (int i = 0; i < numsStack.size(); i++) {
      int num = numsStack.get(i);
      if (i % 2 == 0)
        results.add(Math.sqrt(num));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for3Stack() {
    List<Double> results = build();
    for (int i : numsStack) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> steam1Stack() {
    List<Double> results = build();
    results = numsStack.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toList());
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorForStack() {
    List<Double> results = build();
    for (Iterator<Integer> iter = numsStack.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorWhileStack() {
    List<Double> results = build();
    Iterator<Integer> iter = numsStack.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> listIteratorForStack() {
    List<Double> results = build();
    for(ListIterator<Integer> iter = numsStack.listIterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> listIteratorWhileStack() {
    List<Double> results = build();
    ListIterator<Integer> iter = numsStack.listIterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> whileStackPop() {
    List<Double> results = build();
    Stack<Integer> numsStackCloned = (Stack<Integer>) numsStack.clone();
    while(!numsStackCloned.empty()) {
      int i = numsStackCloned.pop();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }
  
  // hashset
  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for3HashSet() {
    List<Double> results = build();
    for (int i : numsHashSet) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> steam1HashSet() {
    List<Double> results = build();
    results = numsHashSet.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toList());
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorForHashSet() {
    List<Double> results = build();
    for (Iterator<Integer> iter = numsHashSet.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorWhileHashSet() {
    List<Double> results = build();
    Iterator<Integer> iter = numsHashSet.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }
  
  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> spliteratorHashSet() {
    List<Double> results = build();
    Spliterator<Integer> spliterator = numsHashSet.spliterator();
    spliterator.forEachRemaining(i -> {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    });
    assertEquals(results.size(), expectedResultSize);
    return results;
  }
  
  // linkedhashset
  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for3LinkedHashSet() {
    List<Double> results = build();
    for (int i : numsLinkedHashSet) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> steam1LinkedHashSet() {
    List<Double> results = build();
    results = numsLinkedHashSet.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toCollection(() -> build()));
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorForLinkedHashSet() {
    List<Double> results = build();
    for (Iterator<Integer> iter = numsLinkedHashSet.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorWhileLinkedHashSet() {
    List<Double> results = build();
    Iterator<Integer> iter = numsLinkedHashSet.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }
  
  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> spliteratorLinkedHashSet() {
    List<Double> results = build();
    Spliterator<Integer> spliterator = numsLinkedHashSet.spliterator();
    spliterator.forEachRemaining(i -> {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    });
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  // treeset
  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> for3TreeSet() {
    List<Double> results = build();
    for (int i : numsTreeSet) {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> steam1TreeSet() {
    List<Double> results = build();
    results = numsTreeSet.stream()
      .filter(num -> num % 2 == 0)
      .map(Math::sqrt)
      .collect(Collectors.toCollection(() -> build()));
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorForTreeSet() {
    List<Double> results = build();
    for (Iterator<Integer> iter = numsTreeSet.iterator(); iter.hasNext();) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> iteratorWhileTreeSet() {
    List<Double> results = build();
    Iterator<Integer> iter = numsTreeSet.iterator();
    while (iter.hasNext()) {
      Integer i = iter.next();
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    }
    assertEquals(results.size(), expectedResultSize);
    return results;
  }
  
  @Benchmark
  @OperationsPerInvocation(N)
  public Collection<Double> spliteratorTreeSet() {
    List<Double> results = build();
    Spliterator<Integer> spliterator = numsTreeSet.spliterator();
    spliterator.forEachRemaining(i -> {
      if (i % 2 == 0)
        results.add(Math.sqrt(i));
    });
    assertEquals(results.size(), expectedResultSize);
    return results;
  }

  private List<Double> build() {
    return new ArrayList<>();
  }
  
  private void assertEquals(int actual, int expected) {
    if (actual != expected)
      throw new RuntimeException("expected: " + expected + ", actual: " + actual);
  }
  
}
