package pl.onyszko.brighter.states;

import pl.onyszko.brighter.interfaces.StateInterface;
import pl.onyszko.brighter.utils.GameStateManager;

/**
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public abstract class BaseState implements StateInterface {

    protected GameStateManager gsm;

    protected BaseState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }
}
