package de.mathblobs.entities;

import de.mathblobs.Main;

public class Player extends Entity {

    public Player(int x, int y) {
        super(x, y, 20, 20, Main.entityHandler);
    }

    @Override
    public void collide(Entity other) {
        System.out.println("Collided with: " + other.getClass().getName());
    }

    @Override
    public void draw() {
        super.draw();
    }
}
