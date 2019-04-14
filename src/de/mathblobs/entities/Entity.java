package de.mathblobs.entities;

import de.mathblobs.main.Drawable;
import de.mathlib.Vector2;
import processing.core.PApplet;

public abstract class Entity implements Drawable {

    protected Vector2 pos;
    protected Vector2 size;
    public static PApplet pa;

    public Entity(int x, int y, int width, int height) {
        this(new Vector2(x, y), new Vector2(width, height));
    }

    public Entity(Vector2 pos, Vector2 size) {
        this.pos = pos;
        this.size = size;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
}
