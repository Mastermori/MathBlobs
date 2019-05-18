package de.mathblobs.tasks;

public class SubTask implements Task {

    Task t1;
    Task t2;

    public SubTask(Task t1, Task t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public String getTask() {
        return t1.getTask() + " - " + t2.getTask();
    }

    @Override
    public float getSolution() {
        return t1.getSolution() - t2.getSolution();
    }
}
