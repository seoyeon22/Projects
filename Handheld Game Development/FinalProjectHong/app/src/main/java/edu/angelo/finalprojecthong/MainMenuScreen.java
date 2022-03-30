package edu.angelo.finalprojecthong;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

import java.util.List;

public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                }
                if (inBounds(event, 64, 220, 192, 42)) {
                    game.setScreen(new GameScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }
                if (inBounds(event, 64, 220 + 42, 192, 42)) {
                    game.setScreen(new HighscoreScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }
                if (inBounds(event, 64, 220 + 84, 192, 42)) {
                    /**
                     * selecting HELP from the main menu sends instructions in a browser
                     */
                    final Uri webpageUri = Uri.parse("https://en.wikipedia.org/wiki/Asteroids_(video_game)"); // create a Uri object that points to a webpage
                    final Intent webpageIntent = new Intent(Intent.ACTION_VIEW, webpageUri); // create an Intent to open that webpage
                    ((AndroidGame) game).startActivity(webpageIntent); // use the Intent to open that webpage in the device's browser

                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1) {
            return true;
        } else {
            return false;
        }
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawRect(0, 0, World.WORLD_WIDTH, World.WORLD_HEIGHT, Color.rgb(100, 100, 100));
        g.drawPixmap(Assets.logo, 32, 20);
        g.drawPixmap(Assets.mainMenu, 64, 220);
        g.drawPixmap(Assets.credit, 32, 340);
        if (Settings.soundEnabled) {
            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        } else {
            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        }
    }

    public void pause() {
        Settings.save(game.getFileIO());
    }

    public void resume() {
    }

    public void dispose() {
    }
}
