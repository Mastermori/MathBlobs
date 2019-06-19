package de.mathblobs.entities;

import de.mathblobs.Main;
import de.mathlib.Vector2;
import de.ssjgl.Game;

public class StandingSpot {

    private static Main pa = (Main) Game.inst;

    private Vector2 pos, size;
    private int color;
    private SpotHandler handler;

    public StandingSpot(int x, int y, int width, int height, int color, SpotHandler handler) {
        pos = new Vector2(x, y);
        size = new Vector2(width, height);
        this.color = color;
        this.handler = handler;
        handler.addSpot(this);
    }

    public void draw() {
        pa.stroke(color);
        pa.noFill();
        pa.ellipse(pos.x, pos.y, size.x, size.y);
        pa.noStroke();
    }

    @Override
    public String toString() {
        return "{" + pos.toString() + " ; " + size.toString() + "}";
    }

    public Vector2 getHorizontalColliders() {
        return new Vector2(pos.x - size.x/2, pos.x + size.x/2);
    }

    public Vector2 getVerticalColliders() {
        return new Vector2(pos.y - size.y/2, pos.y + size.y/2);
    }

    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getSize() {
        return size;
    }

    public Vector2 getMiddle() {
        return pos.sub(size.div(2));
    }
}
