package de.mathblobs.entities;

import java.util.LinkedList;
import java.util.List;

public class EntityHandler {

    /**
     * Used to handle multiple entities (running update and draw methods)
     */

    //---------- VARIABLES ----------
    private List<Entity> entities;
    private List<Entity> rem;
    private List<Entity> add;

    //---------- CONSTRUCTORS ----------
    public EntityHandler() {
        entities = new LinkedList<>();
        rem = new LinkedList<>();
        add = new LinkedList<>();
    }

    //---------- UPDATE ----------
    public void update(float delta) {
        updateLists();

        entities.forEach((e) -> e.update(delta));
    }

    //---------- DRAW ----------
    public void draw() {
        entities.forEach((e) -> e.draw());
    }

    //---------- COLLISION CHECK ----------
    public void checkCollision(EntityHandler handler) {
        List<Entity> otherEntities = handler.entities;
        for (Entity e : entities) {
            for (Entity oe : otherEntities) {
                if (checkEntityCollision(e, oe)) {
                    e.collide(oe);
                    oe.collide(e);
                }
            }
        }
    }

    private boolean checkEntityCollision(Entity e1, Entity e2) {
        return e1.getHorizontalColliders().y > e2.getHorizontalColliders().x
                && e1.getHorizontalColliders().x < e2.getHorizontalColliders().x
                && e1.getVerticalColliders().y > e2.getVerticalColliders().x
                && e1.getVerticalColliders().x < e2.getVerticalColliders().y;
    }

    //---------- LIST MANAGEMENT METHODS ----------
    private void updateLists() {
        if(!rem.isEmpty()) {
            rem.forEach((e) -> entities.remove(e));
            rem.clear();
        }
        if(!add.isEmpty()) {
            add.forEach((e) -> entities.add(e));
            add.clear();
        }
    }

    public void addEntity(Entity entity) {
        add.add(entity);
    }
    public void removeEntity(Entity entity) {
        rem.add(entity);
    }

}
