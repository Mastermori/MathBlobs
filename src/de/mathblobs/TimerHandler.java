package de.mathblobs;

import java.util.HashMap;

public class TimerHandler {

    private HashMap<String, Timer> timers;

    public TimerHandler() {
        timers = new HashMap<>();
    }

    public void update(float delta) {
        timers.values().forEach((t) -> t.update(delta));
    }

    public void addTimer(String name, Timer timer) {
        timers.put(name, timer);
    }

    public void removeTimer(String name) {
        timers.remove(name);
    }

    public void removeTimer(Timer timer) {
        timers.remove(timer);
    }

    public Timer getTimer(String name) {
        return timers.get(name);
    }

}
