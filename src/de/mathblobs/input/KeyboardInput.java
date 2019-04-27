package de.mathblobs.input;

import de.mathblobs.Main;

import java.util.*;

public class KeyboardInput {

    private static Main pa = Main.inst;

    private static Set<Integer> justPressed = new HashSet<>();
    private static Set<Integer> pressed = new HashSet<>();

    public static boolean keyDown(int keyCode) {
        return pressed.contains(keyCode);
    }

    public static boolean keyJustDown(int keyCode) {
        return justPressed.contains(keyCode);
    }

    public static void keyPressed(int keyCode, char key) {
        justPressed.add(keyCode);
        pressed.add(keyCode);
    }

    public static void keyReleased(int keyCode, char key) {
        pressed.remove(Integer.valueOf(keyCode));
    }

    public static void update(float delta) {
            justPressed.clear();
    }


}
