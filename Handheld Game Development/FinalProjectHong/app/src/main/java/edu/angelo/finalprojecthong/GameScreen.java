package edu.angelo.finalprojecthong;

/**
 * Seoyeon Hong
 */

import java.util.List;

import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen {

    /**
     * contain information of which button is pressed while the user is pressing it.
     */
    int c = 0;

    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";

    /**
     * use matrix object for image rotation.
     */
    Matrix matrix;

    public GameScreen(Game game) {
        super(game);
        world = new World();
        matrix = new Matrix();

        if (Settings.soundEnabled){
            Assets.screen_change.play(1);
        }
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (state == GameState.Ready) {
            updateReady(touchEvents);
        }
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }
        if (state == GameState.Paused) {
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();

        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled) {
                        Assets.paused.play(1);
                    }
                    state = GameState.Paused;
                    return;
                }

                c = 0; // set c 0 when touch up event occurred.

            }

            /**
             * whenever a TOUCH_DOWN event occurs and the y-coordinate of the touch is at least 64,
             * calls the shoot method of the world object
             */
            if (event.type == TouchEvent.TOUCH_DOWN  || event.type == TouchEvent.TOUCH_DRAGGED) {

                // check if rotate, move, shoot button is pressed.
                if(event.y > World.WORLD_HEIGHT - 48){
                    // rotate left button
                    if(event.x < 48){
                        c = 1;
                    }
                    // rotate right button
                    else if(event.x < 98){
                        c = 2;
                    }
                    // move button
                    else if(event.x > World.WORLD_WIDTH - 50){
                        c = 3;
                    }
                    // shoot button
                    else if(event.x > World.WORLD_WIDTH - 100){
                        world.shoot();
                        if (Settings.soundEnabled){
                            Assets.laser_shot.play(1); // play shooting sound
                        }

                    }
                }
            }
        }

        world.update(deltaTime);

        /**
         * if user is pressing rotate or move button, call click_button method to take action.
         */
        if(c != 0){
            click_button(c);
        }

        if (world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.gameover_sound.play(1);
            }
            state = GameState.GameOver;
        }

        /**
         * play sound only when user shot the target and soundEnabled is true.
         */
        if (oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;

            if(Settings.soundEnabled){
                Assets.destroy.play(1);
            }
        }

    }

    /**
     * rotate or move spacecraft
     * @param c contains information of which button is pressed.
     */
    public void click_button(int c){
            switch(c){
                case 1:
                    world.spaceCraft.angle = (world.spaceCraft.angle + 15) % 360;
                    break;
                case 2:
                    world.spaceCraft.angle = (world.spaceCraft.angle + 345) % 360;
                    break;
                case 3:
                    world.spaceCraft.len = 20;
                    break;
            }
    }


    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 80 && event.x <= 240) {
                    if (event.y > 100 && event.y <= 148) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        state = GameState.Running;
                        return;
                    }
                    if (event.y > 148 && event.y < 196) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        /**
                         * whenever the user chooses the quit option,
                         * adds the score to the high scores and saves them to file
                         */
                        Settings.addScore(world.score);
                        Settings.save(game.getFileIO());

                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawRect(0, 0, World.WORLD_WIDTH, World.WORLD_HEIGHT, Color.rgb(10, 10, 40)); // dark blue background
        drawWorld(world);
        if (state == GameState.Ready) {
            drawReadyUI();
        }
        if (state == GameState.Running) {
            drawRunningUI();
        }
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }

        /**
         * write the scor ein the upper right of the screen.
         */
        drawText(g, score, g.getWidth() - score.length() * 20, 0);
    }

    private void drawWorld(World world) {
        Graphics g = game.getGraphics();

        /**
         * draw rectangle that indicates the shooting area.
         */
        //g.drawRect(0, 0, World.WORLD_WIDTH, World.WORLD_HEIGHT, Color.rgb(10, 10, 40));

        /**
         * draw each of the targets in its correct location.
         */
        for(int i = 0; i < world.targetSet.targets.size(); i++){
            g.drawPixmap(Assets.asteroid, world.targetSet.targets.get(i).locationX - 16, world.targetSet.targets.get(i).locationY - 16);
        }

        /**
         * draw each of the targets in its correct location and use cracked asteroid image if it is hit once.
         */
        for(int i = 0; i < world.targetSet2.targets.size(); i++){
            if(world.targetSet2.targets.get(i).hitNum == 0){
                g.drawPixmap(Assets.asteroid2, world.targetSet2.targets.get(i).locationX - 24, world.targetSet2.targets.get(i).locationY - 24);
            }
            else{
                g.drawPixmap(Assets.asteroid2_crack, world.targetSet2.targets.get(i).locationX - 24, world.targetSet2.targets.get(i).locationY - 24);
            }
        }

        /**
         * draw each of the bullets in its correct location.
         */
        for(Bullet b : world.bulletSet.bullets){
            if(b.draw){
                g.drawCircle(b.locationX, b.locationY, 4, Color.WHITE);
            }
        }

        /**
         * rotate image of spacecraft
         */
        matrix.reset();
        matrix.setTranslate(world.spaceCraft.locationX - 16, world.spaceCraft.locationY - 16);
        matrix.postRotate(-((float)world.spaceCraft.angle - 90), world.spaceCraft.locationX, world.spaceCraft.locationY);

        /**
         * use spacecraft_move when spacecraft is moving
         */
        if(world.spaceCraft.len > 8){
            g.drawPixmap(Assets.spacecraft_move, matrix);
        }
        else{
            g.drawPixmap(Assets.spacecraft, matrix);
        }
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.ready, 47, 100);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        /**
         * draw buttons
         */
        g.drawPixmap(Assets.button_pause, 0, 0);
        g.drawPixmap(Assets.rotate_left, 0, World.WORLD_HEIGHT - 50);
        g.drawPixmap(Assets.rotate_right,50, World.WORLD_HEIGHT - 50);
        g.drawPixmap(Assets.move,World.WORLD_WIDTH - 50, World.WORLD_HEIGHT - 50);
        g.drawPixmap(Assets.shoot, World.WORLD_WIDTH - 100, World.WORLD_HEIGHT - 50);

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.pause, 80, 100);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);

    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i += 1) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;
        }
        if (world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
