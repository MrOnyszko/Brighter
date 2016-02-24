package pl.gratitude.brighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.io.File;

import pl.gratitude.brighter.handlers.AssetsHandler;
import pl.gratitude.brighter.states.MenuState;
import pl.gratitude.brighter.utils.Dictionary;
import pl.gratitude.brighter.utils.GameStateManager;

public class Main extends ApplicationAdapter {

    private static final String TAG = Main.class.getSimpleName();

    private static Main main;
    private GameStateManager mGSM;
    private AssetsHandler<Integer> tilesResource;
    private AssetsHandler<String> userInterfaceResource;

    private int screenWidth;
    private int screenHeight;

    private Main() {}

	@Override
	public void create () {

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
	public void render () {
        Gdx.gl.glClearColor(24 / 255f, 26 / 255f, 33 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mGSM.update(Gdx.graphics.getDeltaTime());
        mGSM.render();
	}

    @Override
    public void dispose() {
        super.dispose();
        mGSM.dispose();
    }

    private void loadResources() {
        try {
            userInterfaceResource = new AssetsHandler<String>();
            tilesResource = new AssetsHandler<Integer>();
            userInterfaceResource.loadAtlas(Dictionary.RESOURCES_BUTTONS, Dictionary.RESOURCES_BUTTONS + Dictionary.TEXT_EXTENSION);
            userInterfaceResource.loadAtlas(Dictionary.RESOURCES_ICONS, Dictionary.RESOURCES_ICONS + Dictionary.TEXT_EXTENSION);
            for (int i = 1; i <= 9; i++) {
                tilesResource.loadAtlas(i, Dictionary.TILE_SPRITES_DIRECTORY + File.separator + i + Dictionary.TEXT_EXTENSION);
            }
        } catch (Exception e) {
            Gdx.app.log(TAG, e.getMessage(), e);
        }
    }

    public static Main getInstance() {
        if(main == null) {
            main = new Main();
        }
        return main;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}
