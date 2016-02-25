package pl.gratitude.brighter.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.gratitude.brighter.utils.Dictionary;

/**
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public class Tile {

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    private boolean brighter;
    private TextureRegion normal;
    private TextureRegion light;

    public Tile(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width - Dictionary.TILE_SPACING;
        this.height = height - Dictionary.TILE_SPACING;
    }

    public boolean isBrighter() {
        return brighter;
    }

    public void setBrighter(boolean brighter) {
        this.brighter = brighter;
    }

    public TextureRegion getNormal() {
        return normal;
    }

    public void setNormal(TextureRegion normal) {
        this.normal = normal;
    }

    public TextureRegion getLight() {
        return light;
    }

    public void setLight(TextureRegion light) {
        this.light = light;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width - 8;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height - 8;
    }

    public void render(SpriteBatch sb) {
        if (brighter) {
            sb.draw(light, x - (width / 2), y - (height / 2), width, height);
        } else {
            sb.draw(normal, x - (width / 2), y - (height / 2), width, height);
        }
    }
}
