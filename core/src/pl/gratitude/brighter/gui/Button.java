package pl.gratitude.brighter.gui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created on 25.02.2016
 *
 * @author Sławomir Onyszko
 */
public class Button extends ImageButton {

    public Button(Texture textureUp, Texture textureDown, Texture textureBackground) {
        super(new SpriteDrawable(new Sprite(textureUp)),
                new SpriteDrawable(new Sprite(textureDown)));

        this.setBackground(new SpriteDrawable(new Sprite(textureBackground)));
    }

    public Button(Sprite textureUp) {
        super(new SpriteDrawable(new Sprite(textureUp)),
                new SpriteDrawable(new Sprite(textureUp)));

        this.setBackground(new SpriteDrawable(new Sprite(textureUp)));
    }

    public Button(Sprite textureUp, float x, float y) {
        super(new SpriteDrawable(new Sprite(textureUp)));
        setPosition((x - (getWidth() / 2)), (y - (getHeight() / 2)));
    }

    public Button(FileHandle fileHandle) {
        super(new SpriteDrawable(new Sprite(new Texture(fileHandle))));
    }
}