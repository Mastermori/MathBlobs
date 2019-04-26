package de.mathblobs;

public class Timer {

    float interval;
    float time;
    SimpleAction action;

    public Timer(float interval, SimpleAction action) {
        this.interval = interval;
        this.action = action;
        time = 0f;
    }

    public void update(float time) {
        this.time += time;
        if (this.time >= interval) {
            this.time -= interval;
            action.execute();
        }
    }


}
