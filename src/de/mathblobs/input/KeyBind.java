package de.mathblobs.input;

import de.mathblobs.logic.KeyAction;
import de.mathlib.Utils;

import java.util.LinkedList;
import java.util.List;

public class KeyBind {

    //---------- VARIABLES ----------

    KeyCodeAction[] keyCodeActions;
    KeyAction action;
    String name;

    //---------- CONSTRUCTOR ----------
    public KeyBind(String name, KeyCodeAction keyCodeAction, KeyAction action) {
        this(name, new KeyCodeAction[]{keyCodeAction}, action);
    }

    public KeyBind(String name, KeyAction action, KeyCodeAction... keyCodeActions) {
        this(name, keyCodeActions, action);
    }

    public KeyBind(String name, KeyCodeAction[] keyCodeActions, KeyAction action) {
        this.name = name;
        this.keyCodeActions = keyCodeActions;
        this.action = action;
    }


    public void checkAction() {
        for (KeyCodeAction keyCodeAction : keyCodeActions) {
            if(!keyCodeAction.isFulfilled())
                return;
        }
        action.execute(getKeyCodes());
    }

    private int[] getKeyCodes() {
        List<Integer> keyCodes = new LinkedList<>();
        for (KeyCodeAction keyCodeAction : keyCodeActions) {
            keyCodes.addAll(keyCodeAction.getKeyCodes());
        }
        return Utils.listToArray(keyCodes);
    }

}
