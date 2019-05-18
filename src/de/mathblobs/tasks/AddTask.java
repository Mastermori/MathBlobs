package de.mathblobs.tasks;

public class AddTask implements Task {

    Task t1;
    Task t2;

    public AddTask(Task t1, Task t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public float getSolution() {
        return t1.getSolution() + t2.getSolution();
    }

    @Override
    public String getTask() {
        return t1.getTask() + " + " + t2.getTask();
    }
}
