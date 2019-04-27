package de.mathblobs.entities;

import de.mathblobs.Main;
import de.mathlib.Vector2;

public abstract class Entity {

    protected static Main pa = Main.inst;

    protected Vector2 pos;
    protected Vector2 size;

    protected Vector2 vel;
    protected Vector2 acc;

    protected EntityHandler handler;

    public Entity(int x, int y, int width, int height, EntityHandler handler) {
        this(new Vector2(x, y), new Vector2(width, height), handler);
    }

    public Entity(Vector2 pos, Vector2 size, EntityHandler handler) {
        this.pos = pos;
        this.size = size;
        this.handler = handler;
        handler.addEntity(this);
        vel = new Vector2(0, 0);
        acc = new Vector2(0, 0);
    }

    public void update(float delta){
        vel = vel.add(acc.mult(delta));
        pos = pos.add(vel.mult(delta));
    }

    public void draw(){
        pa.fill(0);
        pa.rect(pos.x, pos.y, size.x, size.y);
    }

    public abstract void collide(Entity other);

    public Vector2 getHorizontalColliders() {
        return new Vector2(pos.x, pos.x + size.x);
    }

    public Vector2 getVerticalColliders() {
        return new Vector2(pos.y, pos.y + size.y);
    }

    public void addVel(Vector2 add) {
        vel = vel.add(add);
    }

    public void addAcc(Vector2 add) {
        acc = acc.add(add);
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

    public void destroy() {
        handler.removeEntity(this);
    }
}
