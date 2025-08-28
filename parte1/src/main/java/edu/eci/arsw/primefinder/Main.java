package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Controller controller = new Controller();
    List<PrimeFinderThread> threads = new ArrayList<>();

    threads.add(new PrimeFinderThread(0, 10000000, "A", controller));
    threads.add(new PrimeFinderThread(10000001, 20000000, "B", controller));
    threads.add(new PrimeFinderThread(20000001, 30000000, "C", controller));

    threads.forEach(Thread::start);

    //    Pausamos main durante 5 segundos, mientras los hilos se ejecutan
    Thread.sleep(5000);

    //    Luego de 5 segundos, main continúa para asi pausar los hilos
    System.out.println("\nPausing threads...");
    controller.pause();

    //    Esperamos que el usuario presione ENTER
    System.out.printf("%nPrimes found: %d%n", countPrimes(threads));
    System.out.println("\nPress ENTER to continue...");
    new Scanner(System.in).nextLine();

    //    Reanudamos los hilos
    System.out.println("\nResuming threads...");
    controller.resume();

    //    Esperamos a que todos los hilos finalicen su ejecución
    for (Thread t : threads) {
      t.join();
    }

    System.out.printf("%nTotal primes found: %d%n", countPrimes(threads));
  }

  private static int countPrimes(List<PrimeFinderThread> threads) {
    int totalPrimes = 0;

    for (PrimeFinderThread t : threads) {
      totalPrimes += t.getPrimes().size();
    }

    return totalPrimes;
  }
}
