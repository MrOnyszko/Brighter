package pl.gratitude.brighter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import pl.gratitude.brighter.Main;
import pl.gratitude.brighter.utils.Dictionary;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Dictionary.GAME_TITLE;
        config.width = Dictionary.VIRTUAL_WIDTH;
        config.height = Dictionary.VIRTUAL_HEIGHT;
        new LwjglApplication(new Main(), config);
    }
}
