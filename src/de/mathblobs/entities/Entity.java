package de.mathblobs.entities;

import de.mathblobs.Main;
import de.mathlib.Vector2;

public abstract class Entity {

    protected static Main pa = Main.inst;

    protected Vector2 pos;
    protected Vector2 size;

    protected Vector2 vel;
    protected Vector2 acc;

    public Entity(int x, int y, int width, int height) {
        this(new Vector2(x, y), new Vector2(width, height));
    }

    public Entity(Vector2 pos, Vector2 size) {
        this.pos = pos;
        this.size = size;
        vel = new Vector2(0, 0);
        acc = new Vector2(0, 0);
        Main.entityHandler.addEntity(this);
    }

    public void update(float delta){
        vel = vel.add(acc.mult(delta));
        pos = pos.add(vel.mult(delta));
    }

    public void draw(){
        pa.fill(0);
        pa.rect(pos.x, pos.y, size.x, size.y);
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

    public void destory() {
        Main.entityHandler.removeEntity(this);
    }
}
