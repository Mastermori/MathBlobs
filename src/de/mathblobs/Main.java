package de.mathblobs;

import de.mathblobs.entities.EntityHandler;
import de.mathblobs.entities.Player;
import de.mathblobs.entities.Projectile;
import de.mathblobs.input.KeyboardInput;
import de.mathlib.Vector2;
import processing.core.PApplet;

public class Main extends PApplet {

    public static Main inst;

    public static String title = "Mathematic Blobs";

    public static EntityHandler entityHandler;
    public static EntityHandler projectileHandler;

    public static TimerHandler timerHandler;

    public static Player player;
    public static Projectile bullet;

    static long lastTime;

    public Main(){
        inst = this;
    }

    @Override
    public void setup() {
        entityHandler = new EntityHandler();
        projectileHandler = new EntityHandler();

        timerHandler = new TimerHandler();

        timerHandler.addTimer("FPS", new Timer(1f, () -> surface.setTitle(title + "     " + frameRate)));

        player = new Player(100, 100);

        bullet = new Projectile(200, 110, 7, 7, color(255, 0, 20));
        bullet.addVel(new Vector2(-20f, 0));

        frameRate(120);
    }

    @Override
    public void draw() {
        //Sets processing draw parameters and draws background
        background(100);
        noStroke();
        smooth();

        //Delta time calculation
        long currentTime = System.currentTimeMillis();
        if (lastTime == 0) {
            lastTime = currentTime;
        }
        float delta = (currentTime - lastTime) / 1000f;
        lastTime = currentTime;

        if(KeyboardInput.keyJustDown(32))
            System.out.println("SPACE");

        projectileHandler.update(delta);
        entityHandler.update(delta);
        timerHandler.update(delta);

        entityHandler.checkCollision(projectileHandler);

        projectileHandler.draw();
        entityHandler.draw();

        KeyboardInput.update(delta);

    }

    @Override
    public void keyPressed() {
        KeyboardInput.keyPressed(keyCode, key);
        System.out.println(keyCode);
    }

    @Override
    public void keyReleased() {
        KeyboardInput.keyReleased(keyCode, key);
    }

    @Override
    public void settings() {
        size(1080, 720);
    }

    public static void main(String[] args) {
        PApplet.main("de.mathblobs.Main");
    }
}
