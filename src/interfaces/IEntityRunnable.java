package interfaces;

public interface IEntityRunnable extends Runnable {
    void pause();
    void resume();
    void stop();
    boolean isRunning();
}

