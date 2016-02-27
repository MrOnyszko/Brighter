package pl.gratitude.brighter.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created on 27.02.2016
 *
 * @author Sławomir Onyszko
 */
public class FreeTypeLabel {

    private Color color;
    private BitmapFont font;
    private GlyphLayout layout;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private int fontSize;

    private float x;
    private float y;
    private float width;
    private float height;
    private float density;

    public FreeTypeLabel(CharSequence text, int fontSize, float x, float y) {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ubuntu-regular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        density = Gdx.graphics.getDensity();
        if(density < 1) density = 2;
        fontParameter.size = Math.round(fontSize * density);
        font = fontGenerator.generateFont(fontParameter);

        layout = new GlyphLayout();
        layout.setText(font, text);

        width = layout.width;
        height = layout.height;

        this.x = x - (width / 2);
        this.y = y - (height / 2);

        color = Color.WHITE;

        fontGenerator.dispose();

    }

    public void draw(SpriteBatch sb) {
        font.setColor(color);
        font.draw(sb, layout, x, y);
    }

    public void dispose() {
        font.dispose();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
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

    public int getFontSize() {
        return fontSize;
    }

    public void setCords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
