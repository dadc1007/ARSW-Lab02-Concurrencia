package edu.eci.arsw.primefinder;

public class Controller {
  public boolean paused = false;

  public synchronized void pause() {
    paused = true;
  }

  public synchronized void resume() {
    paused = false;
    notifyAll(); // Despertamos todos los hilos que están en espera
  }

  public synchronized void waitIfPaused() {
    while (paused) {
      try {
        wait(); // El hilo queda en estado WAITING hasta que lo despierten
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
