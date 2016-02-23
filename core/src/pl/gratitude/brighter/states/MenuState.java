package pl.gratitude.brighter.states;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import pl.gratitude.brighter.utils.GameStateManager;
import pl.gratitude.brighter.utils.Util;

/**
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public class MenuState extends BaseState {

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void render() {
        super.render();
        sr.setColor(Util.hexToRGBA("#445FCCAA"));
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(300, 400, 50);
        sr.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
