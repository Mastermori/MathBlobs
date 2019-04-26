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

        updateEntities();

        entities.forEach((e) -> e.update(delta));
    }

    //---------- DRAW ----------
    public void draw() {
        updateEntities();

        entities.forEach((e) -> e.draw());
    }

    //---------- LIST MANAGEMENT METHODS ----------
    private void updateEntities() {
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
