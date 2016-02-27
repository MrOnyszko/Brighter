package pl.gratitude.brighter.gui;

/**
 * Created on 27.02.2016
 *
 * @author Sławomir Onyszko
 */
public class Label extends com.badlogic.gdx.scenes.scene2d.ui.Label {

    private String text;

    public Label(CharSequence text, LabelStyle style) {
        super(text, style);
        this.text = text.toString();
    }

    @Override
    public void act(final float delta) {
        this.setText(text);
        super.act(delta);
    }

    public void updateText(final String text) {
        this.text = text;
    }

}
