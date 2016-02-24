package pl.gratitude.brighter.utils;

import java.util.Stack;

import pl.gratitude.brighter.interfaces.StateInterface;
import pl.gratitude.brighter.states.BaseState;

/**
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public class GameStateManager implements StateInterface {

    private Stack<BaseState> states;

    public GameStateManager() {
        states = new Stack<BaseState>();
    }

    public void push(BaseState baseState) {
        states.push(baseState);
    }

    public void pop() {
        states.pop();
    }

    public void set(BaseState baseState) {
        states.pop();
        states.push(baseState);
    }

    @Override
    public void resize(int width, int height) {
        states.peek().resize(width, height);
    }

    @Override
    public void update(float dt) {
        states.peek().update(dt);
    }

    @Override
    public void render() {
        states.peek().render();
    }

    @Override
    public void dispose() {
        states.peek().dispose();
    }
}
