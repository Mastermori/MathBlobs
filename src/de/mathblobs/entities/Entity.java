package de.mathblobs.entities;

import de.mathlib.Vector2;
import static de.mathblobs.main.Main.pa;

public abstract class Entity {

    protected Vector2 pos;
    protected Vector2 size;

    public Entity(int x, int y, int width, int height) {
        this(new Vector2(x, y), new Vector2(width, height));
    }

    public Entity(Vector2 pos, Vector2 size) {
        this.pos = pos;
        this.size = size;
        pa.println("Test successful");
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
}
