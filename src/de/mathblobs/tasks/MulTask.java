package de.mathblobs.tasks;

public class MulTask implements Task {

    Task t1;
    Task t2;

    public MulTask(Task t1, Task t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public String getTask() {
        return "(" + t1.getTask() + ") * (" + t1.getTask() + ")";
    }

    @Override
    public float getSolution() {
        return t1.getSolution() * t2.getSolution();
    }
}
