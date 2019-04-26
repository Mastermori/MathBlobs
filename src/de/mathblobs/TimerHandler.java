package de.mathblobs;

import java.util.HashMap;

public class TimerHandler {

    HashMap<String, Timer> timers;

    public TimerHandler() {
        timers = new HashMap<>();
    }

    public void update(float delta) {
        timers.values().forEach((t) -> t.update(delta));
    }

}
