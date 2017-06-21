package fancy;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author rajanranga541@gmail.com
 */
public class Measure {

    private static Timer timer;
    private long milliseconds;
    private TimerTask task;
    private boolean dead, paused;

    public Measure() {
        this.dead = true;
    }

    public boolean isDead() {
        return this.dead;
    }

    public long getMeasure() {
        return this.milliseconds;
    }

    public void pause() throws NullPointerException {
        if (this.dead) {
            throw new NullPointerException("Object Dead or Not Yet Started");
        } else {
            this.paused = true;
        }
    }

    public void resume() throws NullPointerException {
        if (this.dead) {
            throw new NullPointerException("Object Dead or Not Yet Started");
        } else {
            this.paused = false;
        }
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void start() throws Exception {
        if (this.paused) {
            this.resume();
        }
        if (this.dead) {
            this.milliseconds = 0;
            this.dead = false;
            timer = new java.util.Timer();
            this.task = new TimerTask() {
                @Override
                public void run() {
                    if (!paused) {
                        Measure.this.milliseconds++;
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 1);
        } else if (!this.dead) {
            throw new Exception("Object already running");
        }
    }

    public long stop() throws NullPointerException {
        if (this.dead) {
            throw new NullPointerException("Object dead or Not Yet Started");
        } else {
            this.dead = true;
        }
        this.task.cancel();
        return this.milliseconds;
    }
}
