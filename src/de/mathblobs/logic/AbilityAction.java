package de.mathblobs.logic;

import de.mathblobs.entities.Entity;

@FunctionalInterface
public interface AbilityAction {

    void execute(Entity executor);

}
