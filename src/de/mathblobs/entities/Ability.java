package de.mathblobs.entities;

import de.guilib.Text;
import de.mathblobs.Main;
import de.mathblobs.tasks.AddTask;
import de.mathblobs.tasks.Num;
import de.mathblobs.tasks.SubTask;
import de.mathblobs.tasks.Task;
import de.mathlib.Utils;
import de.ssjgl.Game;
import processing.core.PApplet;

public class Ability {

    private static Main pa = (Main) Game.inst;

    public static final int EASY = 0, MEDIUM = 1, HARD = 2;
    public static final int ADD = 0, SUB = 1, MULT = 2, DIV = 3;

    private Entity parent;
    private Task task;
    private AbilityAction action;
    private Text taskText;
    private int difficulty;
    private String name;

    public Ability(String name, Entity parent, int difficulty, AbilityAction action) {
        this.name = name;
        this.parent = parent;
        this.action = action;
        this.difficulty = difficulty;
        taskText = new Text("", pa.width - 20, 0, Main.font, pa.color(0), PApplet.RIGHT);
        genTask();
        Main.guiHandler.add(taskText);
    }

    public void genTask() {
        /*int taskAmount = (int) pa.random(difficulty/10) + 1;
        List<Integer> taskTypes = new LinkedList<>();
        while(taskAmount-- > 0) {
            int taskType = (int) Math.max(pa.random(difficulty / 5), 3);

        }*/
        int taskType = (int) pa.random(Math.max(difficulty + 1, 1));
        if (taskType == ADD) {
            task = new AddTask(new Num(Utils.round(pa.random((difficulty + 1) * 10), (int) (difficulty / 2.0))),
                    new Num(Utils.round(pa.random((difficulty + 1) * 10), (int) (difficulty / 2.0))));
        } else if (taskType == SUB) {
            float num1 = Utils.round(pa.random((difficulty + 1) * 10) + 1, (int) (difficulty / 2.0));
            float num2;
            while ((num2 = Utils.round(pa.random((difficulty + 1) * 10), (int) (difficulty / 2.0))) > num1) ;
            task = new SubTask(new Num(num1), new Num(num2));
        } else if (taskType == MULT) {
            int num1;
        } else if (taskType == DIV) {

        } else {
            new Exception("task type out of range").printStackTrace();
        }
        if (parent.sameSolution(task.getSolution()))
            genTask();
        else {
            taskText.setText(task.getTask());
            taskText.setY((int) (parent.getAbilityID(this) * Main.font.getSize() * 1.5 + Main.font.getSize() * 5));
        }
    }

    public void checkTask(float solution) {
        if (Utils.round(task.getSolution(), 1) == Utils.round(solution, 1)) {
            action.execute(parent);
            genTask();
        }
    }

    public boolean sameSolution(float solution) {
        return task.getSolution() == solution;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }
}
