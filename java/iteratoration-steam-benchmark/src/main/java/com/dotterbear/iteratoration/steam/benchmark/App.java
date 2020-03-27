package com.dotterbear.iteratoration.steam.benchmark;

public class App {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                    .include(IteratorationSteamBenchmark.class.getSimpleName())
                    .forks(1)
                    .build();
    new Runner(opt).run();
  }

}
