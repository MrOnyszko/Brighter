package pl.gratitude.brighter.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * This class handles texture atlases for resources created from texturepacker.
 * <p/>
 * {@see https://www.codeandweb.com/}
 * {@link TextureAtlas}
 * <p/>
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public class AssetsHandler<T> {

    private HashMap<T, TextureAtlas> map;

    public AssetsHandler() {
        map = new HashMap<T, TextureAtlas>();
    }

    public void loadAtlas(T key, String path) {
        map.put(key, new TextureAtlas(Gdx.files.internal(path)));
    }

    public TextureAtlas getAtlas(T key) {
        return map.get(key);
    }

    public TextureRegion getRegion(T key, String name) {
        return map.get(key).findRegion(name);
    }

    public Sprite getSprite(T key, String name) {
        return map.get(key).createSprite(name);
    }

}
