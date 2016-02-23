package pl.gratitude.brighter.interfaces;

/**
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public interface StateInterface {

    void resize(int width, int height);
    void update(float dt);
    void render();
    void dispose();

}
