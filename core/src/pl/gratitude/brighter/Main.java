package pl.gratitude.brighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import pl.gratitude.brighter.states.MenuState;
import pl.gratitude.brighter.utils.GameStateManager;

public class Main extends ApplicationAdapter {

    private GameStateManager mGSM;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

	@Override
	public void create () {

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

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
}
