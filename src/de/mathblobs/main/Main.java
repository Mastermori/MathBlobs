package de.mathblobs.main;

import de.mathblobs.entities.Player;
import processing.core.PApplet;

public class Main extends PApplet {

    public static Main pa;

    public static Player player;

    public Main(){
        pa = this;
    }

    @Override
    public void setup() {
        player = new Player(100, 100);
    }

    @Override
    public void draw() {

    }

    @Override
    public void settings() {
        size(1080, 720);
    }

    public static void main(String[] args) {
        PApplet.main("de.mathblobs.main.Main");
    }
}
