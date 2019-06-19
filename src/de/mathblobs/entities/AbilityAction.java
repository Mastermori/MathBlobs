package de.mathblobs.entities;

import de.ssjgl.entities.Entity;

@FunctionalInterface
public interface AbilityAction {

    void execute(Entity executor);

}
