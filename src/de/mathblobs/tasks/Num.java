package de.mathblobs.tasks;

import de.mathlib.Utils;

public class Num implements Task {

    float num;

    public Num(float num) {
        this.num = num;
    }

    @Override
    public String getTask() {
        float r = Utils.round(num, 1);
        if (Utils.getDecimal(r, 1) == 0) {
            return String.valueOf((int) r);
        } else {
            return String.valueOf(r);
        }
    }

    @Override
    public float getSolution() {
        return num;
    }
}
