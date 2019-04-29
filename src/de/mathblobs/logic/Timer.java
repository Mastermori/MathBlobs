package de.mathblobs.logic;

public class Timer {

    private float interval;
    private float time;
    private boolean paused;
    private SimpleAction action;

    public Timer(float interval, SimpleAction action) {
        this.interval = interval;
        this.action = action;
        time = 0f;
        paused = false;
    }

    public void update(float time) {
        if (!paused) {
            this.time += time;
            if (this.time >= interval) {
                this.time -= interval;
                action.execute();
            }
        }
    }

    public float getInterval() {
        return interval;
    }
    public void setInterval(float interval) {
        this.interval = interval;
    }

    public boolean isPaused() {
        return paused;
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
