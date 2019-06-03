package de.mathblobs.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Used to handle multiple entities (running update and draw methods)
 */
public class EntityHandler {

    //---------- VARIABLES ----------
    protected List<Entity> entities;
    private List<Entity> rem; //List to remove entities (cant remove in foreach) -> ConcurrentModificationException
    private List<Entity> add; //List to add entities (cant add in foreach) -> ConcurrentModificationException

    //---------- CONSTRUCTORS ----------
    public EntityHandler() {
        //Instantiate class variables (see above)
        entities = new LinkedList<>();
        rem = new LinkedList<>();
        add = new LinkedList<>();
    }

    //---------- UPDATE ----------
    /**
     * Update method called every frame - used to update Lists (add/rem) and call the entities update methods
     * @param delta time used for calculation
     */
    public void update(float delta) {
        updateLists();

        entities.forEach((e) -> e.update(delta));
    }

    //---------- DRAW ----------
    /**
     * Draw method called every frame - used call the entities draw methods
     */
    public void draw() {
        entities.forEach(Entity::draw);
    }

    //---------- COLLISION CHECK ----------

    /**
     * Checks for collision between this and the provided handlers entities.
     * Calls collision method on both entities colliding
     * @param handler to check collisions with
     */
    public void checkCollision(EntityHandler handler) {
        List<Entity> otherEntities = handler.entities;
        for (Entity e : entities) {
            for (Entity oe : otherEntities) {
                if (checkEntityCollision(e, oe)) { //if the entities are colliding, call both collide functions
                    e.collide(oe);
                    oe.collide(e);
                }
            }
        }
    }

    /**
     * Checks collision between to entities using their get..Colliders() methods
     * @param e1
     * @param e2
     * @return true if the entities are colliding (intersecting)
     */
    private boolean checkEntityCollision(Entity e1, Entity e2) {
        return e1.getHorizontalColliders().y > e2.getHorizontalColliders().x
                && e1.getHorizontalColliders().x < e2.getHorizontalColliders().x
                && e1.getVerticalColliders().y > e2.getVerticalColliders().x
                && e1.getVerticalColliders().x < e2.getVerticalColliders().y;
    }

    //---------- LIST MANAGEMENT METHODS ----------
    /**
     * Adds and removes entities from their respective lists and clears the add/rem lists
     */
    private void updateLists() {
        if(!rem.isEmpty()) {
            entities.removeAll(rem);
            rem.clear();
        }
        if(!add.isEmpty()) {
            entities.addAll(add);
            add.clear();
        }
    }

    /**
     * Adds an entity to the handler (added next update call)
     * @param entity
     */
    public void addEntity(Entity entity) {
        add.add(entity);
    }
    /**
     * Removes an entity from the handler (removed next update call)
     */
    public void removeEntity(Entity entity) {
        rem.add(entity);
    }

}
