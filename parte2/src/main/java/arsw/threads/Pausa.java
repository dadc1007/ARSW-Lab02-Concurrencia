package arsw.threads;

public class Pausa {
    private boolean isPaused;

    public synchronized void pause() {
        isPaused = true;
    }

    public synchronized void unpause() {
        isPaused = false;
        notifyAll();
    }

    public synchronized void waitIsPaused() throws InterruptedException {
        while(isPaused){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
