package pl.gratitude.brighter.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import pl.gratitude.brighter.Main;
import pl.gratitude.brighter.gui.Button;
import pl.gratitude.brighter.gui.Label;
import pl.gratitude.brighter.utils.Dictionary;
import pl.gratitude.brighter.utils.GameStateManager;

/**
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public class MenuState extends BaseState {

    /**
     * Log tag.
     */
    private static final String TAG = MenuState.class.getSimpleName();

    private Button play;
    private Label title;

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void create() {

        title = new Label(Dictionary.GAME_TITLE, new Label.LabelStyle(font, Color.WHITE));
        title.setAlignment(Align.center);
        title.setFontScale(8 * density);
        title.setBounds(0, virtualCenterY, Dictionary.VIRTUAL_WIDTH, Dictionary.VIRTUAL_HEIGHT / 2);

        title.setDebug(true);

        play = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.RESOURCES_BUTTONS, Dictionary.Button.PLAY), virtualCenterX, virtualCenterY);
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mGSM.set(new PlayState(mGSM));
            }
        });

        stage.addActor(play);
        stage.addActor(title);
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
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
