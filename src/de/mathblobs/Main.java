package de.mathblobs;

import de.guilib.Box;
import de.guilib.GUIHandler;
import de.guilib.Text;
import de.mathblobs.entities.EntityHandler;
import de.mathblobs.entities.Player;
import de.mathblobs.entities.Projectile;
import de.mathblobs.input.KeyBind;
import de.mathblobs.input.KeyCodeAction;
import de.mathblobs.input.KeyboardInput;
import de.mathblobs.logic.Timer;
import de.mathblobs.logic.TimerHandler;
import de.mathblobs.state.GSM;
import de.mathlib.Vector2;
import processing.core.PApplet;
import processing.core.PFont;

import java.awt.event.KeyEvent;

/**
 * Main class to start and manage most of the game. <br>
 * Contains handlers and direct references to the most important objects. <br>
 * Also contains Processing methods like setup, draw, registerKeyPress, etc.
 */
public class Main extends PApplet {


    //---------- VARIABLES ----------
    public static Main inst; //Reference to the PApplet instance used for drawing and other processing related actions

    private static String title = "Math Blobs"; //Title of the Window

    //Handlers for different Entity groups
    public static EntityHandler friendlyHandler;
    public static EntityHandler friendlyProjectileHandler;
    public static EntityHandler enemyHandler;
    public static EntityHandler enemyProjectileHandler;

    private static GUIHandler guiHandler;
    private static Text inputText;
    private static String inputString;
    public static PFont font;

    public static TimerHandler generalTimerHandler; //Handler for timed-actions (Timers) - actions ran in set intervals
    public static TimerHandler ingameTimerHandler;

    private static Player player; //Player reference
    private static Projectile bullet; //Test-bullet reference

    private static long lastTime; //Used for storing last milliseconds to calculate delta time (in draw)


    //---------- CONSTRUCTOR ----------
    public Main() {
        inst = this; //Set the static PApplet reference to the constructed Main object
    }


    //---------- SETUP ----------
    @Override
    public void setup() {
        //Instantiate class variables (see above)
        friendlyHandler = new EntityHandler();
        friendlyProjectileHandler = new EntityHandler();

        enemyHandler = new EntityHandler();
        enemyProjectileHandler = new EntityHandler();

        font = createFont("Arial", 16);

        guiHandler = new GUIHandler(this, font);

        guiHandler.add(inputText = new Text(inputString = "1234567890", 10+3, height-10, createFont("Arial", 24), color(255, 0, 0), LEFT));
        guiHandler.add(new Box(10, height-24-10+2, 300, 24+5, color(0, 255, 0), false, 3));

        player = new Player(100, 100);

        bullet = new Projectile(200, 110, 7, 7, color(255, 0, 20));
        bullet.addVel(new Vector2(-20f, 0));

        generalTimerHandler = new TimerHandler();
        ingameTimerHandler = new TimerHandler();

        //Add a timer to add the fps counter to the title
        generalTimerHandler.addTimer("window-fps", new Timer(2f, () -> surface.setTitle(title + "     " + (int) frameRate)));
        generalTimerHandler.addTimer("input-flicker", new Timer(0.5f, () -> {
            if(inputText.getText().endsWith("|"))
                inputText.setText(inputString);
            else
                inputText.setText(inputString + "|");
        }));

        //Set Processing parameters
        frameRate(120); //Set max-framerate to 120

        //Add a KeyBind for every number on the keyboard to make inputting numbers possible
        for (int i = 0; i < 10; i++) {
            try {
                KeyboardInput.addBind(new KeyBind("num-in-" + i, new KeyCodeAction(KeyCodeAction.JUSTDOWN, KeyEvent.class.getField("VK_"+i).getInt(null)),
                        (keys) -> {
                            if (inputString.length() < 10) {
                                setInputString(inputString + (keys[0] - KeyEvent.VK_0));
                            }
                        }));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        KeyboardInput.addBind(new KeyBind("num-remove", new KeyCodeAction(KeyCodeAction.JUSTDOWN, KeyEvent.VK_BACK_SPACE), (keys) -> {
            if (inputString.length() > 0) {
                setInputString(inputString.substring(0, inputString.length() - 1));
            }
        }));
        KeyboardInput.addBind(new KeyBind("num-enter", new KeyCodeAction(KeyCodeAction.JUSTDOWN, KeyEvent.VK_ENTER), (keys) ->{
            if (inputString.length() > 0) {
                enterNum(Integer.parseInt(inputString));
                setInputString("");
            }
        }));
        KeyboardInput.addBind(new KeyBind("num-remove-all", new KeyCodeAction(new int[]{KeyCodeAction.DOWN, KeyCodeAction.JUSTDOWN}, new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_BACK_SPACE}),
                (keys) -> {
                    if (inputString.length() > 0) {
                        setInputString("");
                    }
        }));

        GSM.setGameState(GSM.INGAME); //Set the GameState to Ingame (only while testing will be changed to MainMenu once its implemented)
    }


    //---------- DRAW ----------
    @Override
    public void draw() {
        //Sets processing draw parameters and draws background

        //Delta time calculation
        long currentTime = System.currentTimeMillis();

        if (lastTime == 0) {
            lastTime = currentTime; //when the draw method is called for the first time, set the lastTime var
        }

        float delta = (currentTime - lastTime) / 1000f; //Calculate delta time
        lastTime = currentTime; //Set the lastTime var to the currentTime

        //Call the draw methods for each gamestate respectively (order is important for draw layering)
        if (GSM.isGameState(GSM.MAINMENU)) {
            drawMainMenu(delta);
        }
        if (GSM.isGameState(GSM.INGAME)) {
            drawIngame(delta);
        }
        if (GSM.isGameState(GSM.MENU)) {
            drawMenu(delta);
        }
    }

    /**
     * Draws the main menu
     * @param delta delta time for update calculation
     */
    private void drawMainMenu(float delta) {

    }

    /**
     * Draws the game when playing
     * @param delta delta time for update calculation
     */
    private void drawIngame(float delta) {
        background(100); //Set the background color

        if (!GSM.isGameState(GSM.PAUSED)) { //If the game is not paused, call update methods
            enemyProjectileHandler.update(delta);
            friendlyHandler.update(delta);

            generalTimerHandler.update(delta);

            friendlyHandler.checkCollision(enemyProjectileHandler); //Check collisions between friendly and enemyProjectile entities
            enemyHandler.checkCollision(friendlyProjectileHandler); //Check collisions between enemy and friendlyProjectile entities
            friendlyProjectileHandler.checkCollision(enemyProjectileHandler); //Check collisions between friendly and enemy projectile entities

            KeyboardInput.update(delta); //Needs to be last because the justPressed and justReleased lists are cleared here
        }

        enemyProjectileHandler.draw();
        friendlyProjectileHandler.draw();
        enemyHandler.draw();
        friendlyHandler.draw();
        guiHandler.draw();
    }

    /**
     * Draws the ingame menu
     * @param delta delta time for update calculation
     */
    private void drawMenu(float delta) {

    }


    //---------- INPUT ----------
    @Override
    public void keyPressed() {
        KeyboardInput.registerKeyPress(keyCode, key); //Update the KeyboardInput lists
        System.out.println(keyCode);
    }

    @Override
    public void keyReleased() {
        KeyboardInput.registerKeyRelease(keyCode, key); //Update the KeyboardInput lists
    }

    @Override
    public void mouseClicked() {
        guiHandler.mouseClicked();
    }

    private static void enterNum(int num) {

    }

    //---------- GUI ----------
    private static void setInputString(String s) {
        inputString = s;
        inputText.setText(inputString);
    }

    //---------- STARTUP ----------
    @Override
    public void settings() {
        size(1080, 720); //Set the size of the window
        smooth(4); //Set antialiasing from 2x to 4x
    }

    public static void main(String[] args) {
        PApplet.main("de.mathblobs.Main"); //Start Processing with this class as main
    }
}
