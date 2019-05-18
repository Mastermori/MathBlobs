package de.mathblobs.entities;

import de.mathlib.Vector2;

public class Projectile extends Entity {

    int color;

    public Projectile(int x, int y, int width, int height, int color, EntityHandler handler) {
        super(x, y, width, height, handler);
        this.color = color;
        this.onScreenLeave = BOUNCE;
    }

    @Override
    public void draw() {
        pa.fill(color);
        pa.ellipse(pos.x, pos.y, size.x, size.y);
    }

    @Override
    public void collide(Entity other) {
        System.out.println("Collided with " + other.getClass().getName());
        destroy();
    }

    @Override
    public Vector2 getHorizontalColliders() {
        return new Vector2(pos.x - size.x/2, pos.x + size.x/2);
    }

    @Override
    public Vector2 getVerticalColliders() {
        return new Vector2(pos.y - size.y/2, pos.y + size.y/2);
    }
}
