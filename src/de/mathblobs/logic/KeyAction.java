package de.mathblobs.logic;

@FunctionalInterface
public interface KeyAction {
    void execute(int[] keyCodes);
}
