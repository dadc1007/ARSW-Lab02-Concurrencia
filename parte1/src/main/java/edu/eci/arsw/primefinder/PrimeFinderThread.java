package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread {
  private final int a, b;
  private final String name;
  private final Controller controller;
  private final List<Integer> primes = new LinkedList<>();

  public PrimeFinderThread(int a, int b, String name, Controller controller) {
    this.a = a;
    this.b = b;
    this.name = name;
    this.controller = controller;
  }

  @Override
  public void run() {
    for (int i = a; i <= b; i++) {
      controller.waitIfPaused(); // Se detiene aquí si el hilo está pausado

      if (isPrime(i)) {
        primes.add(i);
        System.out.printf("Thread %s found prime: %d%n", name, i);
      }
    }
  }

  boolean isPrime(int n) {
    if (n % 2 == 0) return false;
    for (int i = 3; i * i <= n; i += 2) {
      if (n % i == 0) return false;
    }
    return true;
  }

  public List<Integer> getPrimes() {
    return primes;
  }
}
