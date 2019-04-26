package de.mathblobs.entities;

public class Projectile extends Entity {

    int color;

    public Projectile(int x, int y, int width, int height, int color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void draw() {
        pa.fill(color);
        pa.ellipse(pos.x, pos.y, size.x, size.y);
    }
}
