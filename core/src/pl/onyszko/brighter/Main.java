package pl.onyszko.brighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import pl.onyszko.brighter.states.MenuState;
import pl.onyszko.brighter.utils.GameStateManager;

public class Main extends ApplicationAdapter {

    private GameStateManager mGSM;

	@Override
	public void create () {
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
		Gdx.gl.glClearColor(1, 0, 0, 1);
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
