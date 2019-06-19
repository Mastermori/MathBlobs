package de.mathblobs.entities;

import de.mathlib.Vector2;
import de.ssjgl.entities.EntityHandler;

import java.util.List;

public abstract class Entity extends de.ssjgl.entities.Entity{

    protected List<Ability> abilities;

    public Entity(int x, int y, int width, int height, EntityHandler handler) {
        super(x, y, width, height, handler);
    }

    public Entity(Vector2 pos, Vector2 size, EntityHandler handler) {
        super(pos, size, handler);
    }

    public boolean sameSolution(float solution) {
        System.out.println(abilities.size());
        int appearanceCount = 0;
        for (Ability a : abilities) {
            if (a.sameSolution(solution))
                appearanceCount++;
        }
        return appearanceCount > 1;
    }

    protected int getAbilityID(Ability ability) {
        return abilities.indexOf(ability);
    }

    public void addAbility(Ability ability) {
        abilities.add(ability);
        System.out.println("Added ability");
    }

    public void removeAbility(Ability ability) {
        abilities.remove(ability);
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

}
