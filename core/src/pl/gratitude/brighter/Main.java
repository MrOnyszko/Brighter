package pl.gratitude.brighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.io.File;

import pl.gratitude.brighter.handlers.AssetsHandler;
import pl.gratitude.brighter.states.MenuState;
import pl.gratitude.brighter.utils.Dictionary;
import pl.gratitude.brighter.utils.GameStateManager;

/**
 * The main class of game.
 */
public class Main extends ApplicationAdapter {

    /**
     * Log tag.
     */
    private static final String TAG = Main.class.getSimpleName();

    /**
     * Static istance of {@link Main} class.
     */
    private static Main main;

    /**
     * State manager that maintain game's states.
     */
    private GameStateManager mGSM;

    /**
     * Assets for all tiles.
     * {@link pl.gratitude.brighter.entities.Tile}
     */
    private AssetsHandler<Integer> tilesResource;

    /**
     * Assets for all UI elements.
     */
    private AssetsHandler<String> userInterfaceResource;

    /**
     * Screen width.
     */
    private int screenWidth;

    /**
     * Screen height
     */
    private int screenHeight;

    private Main() {
    }

    @Override
    public void create() {

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        loadResources();

        mGSM = new GameStateManager();
        mGSM.push(new MenuState(mGSM));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        mGSM.resize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(24 / 255f, 26 / 255f, 33 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mGSM.update(Gdx.graphics.getDeltaTime());
        mGSM.render();
        Gdx.graphics.setTitle(Dictionary.GAME_TITLE + " | FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void dispose() {
        super.dispose();
        mGSM.dispose();
        disposeResources();
    }

    /**
     * Load all required resources.
     */
    private void loadResources() {
        try {
            userInterfaceResource = new AssetsHandler<String>();
            tilesResource = new AssetsHandler<Integer>();
            userInterfaceResource.loadAtlas(Dictionary.RESOURCES_BUTTONS, Dictionary.RESOURCES_BUTTONS + File.separator + Dictionary.RESOURCES_BUTTONS + Dictionary.TEXT_EXTENSION);
            userInterfaceResource.loadAtlas(Dictionary.RESOURCES_ICONS, Dictionary.RESOURCES_ICONS + File.separator + Dictionary.RESOURCES_ICONS + Dictionary.TEXT_EXTENSION);
            for (int i = 1; i <= 9; i++) {
                tilesResource.loadAtlas(i, Dictionary.TILE_SPRITES_DIRECTORY + File.separator + i + Dictionary.TEXT_EXTENSION);
                Gdx.app.log(TAG, "Loaded: " + Dictionary.TILE_SPRITES_DIRECTORY + File.separator + i + Dictionary.TEXT_EXTENSION);
            }
        } catch (Exception e) {
            Gdx.app.log(TAG, e.getMessage(), e);
        }
    }

    /**
     * Dispose all loaded resources.
     */
    private void disposeResources() {
        try {
            userInterfaceResource.getAtlas(Dictionary.RESOURCES_BUTTONS).dispose();
            userInterfaceResource.getAtlas(Dictionary.RESOURCES_ICONS).dispose();
            for (int i = 1; i <= 9; i++) {
                tilesResource.getAtlas(i).dispose();
                Gdx.app.log(TAG, "Disposed: " + Dictionary.TILE_SPRITES_DIRECTORY + File.separator + i + Dictionary.TEXT_EXTENSION);
            }
        } catch (Exception e) {
            Gdx.app.log(TAG, e.getMessage(), e);
        }
    }

    /**
     * If null create new instance of {@link Main} object.
     *
     * @return Returns instance of {@link Main} object.
     */
    public static Main getInstance() {
        if (main == null) {
            main = new Main();
        }
        return main;
    }

    /**
     * Get tiles resources assets handler.
     *
     * @return {@link AssetsHandler} specified for tiles assets.
     */
    public AssetsHandler<Integer> getTilesResource() {
        return tilesResource;
    }

    /**
     * Get user interface assets handler.
     *
     * @return {@link AssetsHandler} specified for UI elements.
     */
    public AssetsHandler<String> getUserInterfaceResource() {
        return userInterfaceResource;
    }

    /**
     * Get screen height.
     *
     * @return screen height.
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Get screen width.
     *
     * @return screen width.
     */
    public int getScreenWidth() {
        return screenWidth;
    }
}
