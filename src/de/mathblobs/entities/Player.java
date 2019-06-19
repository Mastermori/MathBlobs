package de.mathblobs.entities;

import de.mathblobs.Main;
import de.ssjgl.animation.Animation;
import de.ssjgl.animation.SpriteSheet;

import java.util.LinkedList;

public class Player extends Entity {

    Animation anim;

    public Player(int x, int y) {
        super(x, y, 32, 32, Main.friendlyHandler);
        abilities = new LinkedList<>();
        anim = new Animation(new SpriteSheet("res/FantasyEnemyCreatures/Ghost/32x32Ghost_FullSheet.png", 32, 32, 0, 0, 0, 0, 6, 1), 10);
        System.out.println("Player constructed");
    }

    @Override
    public void collide(Object other) {
        System.out.println("Collided with: " + other.getClass().getName());
        size = size.div(2); //When colliding with anything, reduce size to half
    }

    @Override
    public void draw() {
        //Draw(); //Use standard draw
        anim.draw(pos.x, pos.y);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        anim.update(delta);
    }

    public void checkAbilities(float num) {
        for (Ability a : abilities) {
            a.checkTask(num);
        }
    }

    public void moveTo(StandingSpot standingSpot) {
        this.pos = standingSpot.getPos().sub(size.mult(0.5f));
    }
}
