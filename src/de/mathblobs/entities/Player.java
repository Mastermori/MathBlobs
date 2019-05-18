package de.mathblobs.entities;

import de.mathblobs.Main;

import java.util.LinkedList;

public class Player extends Entity {

    public Player(int x, int y) {
        super(x, y, 20, 20, Main.friendlyHandler);
        abilities = new LinkedList<>();
    }

    @Override
    public void collide(Entity other) {
        System.out.println("Collided with: " + other.getClass().getName());
        size = size.div(2); //When colliding with anything, reduce size to half
    }

    @Override
    public void draw() {
        Draw(); //Use standard draw
    }

    public void checkAbilities(float num) {
        for (Ability a : abilities) {
            a.checkTask(num);
        }
    }

}
