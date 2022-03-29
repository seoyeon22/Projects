package edu.angelo.finalprojecthong;

/**
 * Seoyeon Hong
 */

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.ARGB8888);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB8888);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB8888);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB8888);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB8888);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB8888);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB8888);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB8888);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB8888);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB8888);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB8888);
        Assets.target = g.newPixmap("target.png", PixmapFormat.ARGB8888);
        Assets.spacecraft = g.newPixmap("spacecraft.png", PixmapFormat.ARGB8888); // image of spacecraft
        Assets.spacecraft_move = g.newPixmap("spacecraft_move.png", PixmapFormat.ARGB8888); // image of moving spacecraft
        Assets.rotate_right = g.newPixmap("rotate_right.png", PixmapFormat.ARGB8888); // rotate_right button image
        Assets.rotate_left = g.newPixmap("rotate_left.png", PixmapFormat.ARGB8888); // rotate_left button image
        Assets.move = g.newPixmap("move.png", PixmapFormat.ARGB8888); // move button image
        Assets.asteroid = g.newPixmap("asteroid.png", PixmapFormat.ARGB8888); // smaller asteroid image
        Assets.asteroid2 = g.newPixmap("asteroid2.png", PixmapFormat.ARGB8888); // bigger asteroid image
        Assets.asteroid2_crack = g.newPixmap("asteroid2_crack.png", PixmapFormat.ARGB8888);
        Assets.button_pause = g.newPixmap("button_pause.png", PixmapFormat.ARGB8888);
        Assets.shoot = g.newPixmap("shoot.png", PixmapFormat.ARGB8888);
        Assets.credit = g.newPixmap("credit.png", PixmapFormat.ARGB8888);
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.gameover_sound = game.getAudio().newSound("gameover.ogg"); // play gameover sound when spacecraft hit asteroid.
        Assets.paused = game.getAudio().newSound("paused.ogg"); // play paused sound when game is paused
        Assets.laser_shot = game.getAudio().newSound("laser_shot.ogg"); // play laser_shot sound when player pressed shoot button
        Assets.destroy = game.getAudio().newSound("destroy.ogg"); // play destruction sound when asteroid is destroyed
        Assets.screen_change = game.getAudio().newSound("screen_change.ogg"); // play screen_change sound when screen is changed

        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    public void present(float deltaTime) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}
