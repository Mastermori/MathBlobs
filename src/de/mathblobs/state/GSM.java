package de.mathblobs.state;

import de.mathlib.Utils;

public class GSM { //GSM = GameStateManager

    public static final int MAINMENU = 1, INGAME = 2, MENU = 4, PAUSED = 8;

    private static int gameState;

    public static void setGameState(int gs) {
        gameState = gs;
    }

    public static boolean isGameState(int state) {
        return Utils.binaryCheck(gameState, state);
    }

    public static int getGameState() {
        return gameState;
    }

}
