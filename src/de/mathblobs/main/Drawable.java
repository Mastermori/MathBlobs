package de.mathblobs.main;

import de.mathlib.Vector2;
import processing.core.PApplet;

public interface Drawable {

    void draw();

    Vector2 getPos();
    Vector2 getSize();

}
