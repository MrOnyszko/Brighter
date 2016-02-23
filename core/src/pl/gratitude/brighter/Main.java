package pl.gratitude.brighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import pl.gratitude.brighter.utils.GameStateManager;

public class Main extends ApplicationAdapter {

    private GameStateManager mGSM;

    public static final int V_WIDTH = 480;
    public static final int V_HEIGHT = 800;
    public static int S_WIDTH;
    public static int S_HEIGHT;

    public static int virtualCenterWidth;
    public static int virtualCenterHeight;

	@Override
	public void create () {
        mGSM = new pl.gratitude.brighter.utils.GameStateManager();
        mGSM.push(new pl.gratitude.brighter.states.MenuState(mGSM));

        S_WIDTH = Gdx.graphics.getWidth();
        S_HEIGHT = Gdx.graphics.getHeight();
        virtualCenterWidth = V_WIDTH / 2;
        virtualCenterHeight = V_HEIGHT / 2;

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
