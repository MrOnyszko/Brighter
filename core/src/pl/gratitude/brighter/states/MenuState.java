package pl.gratitude.brighter.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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

    private static final String TAG = MenuState.class.getSimpleName();

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void create() {

        Label label = createLabel(Dictionary.GAME_TITLE, 50, virtualCenterX, Dictionary.VIRTUAL_HEIGHT - 230, Color.WHITE);

        Button whiteBoxTop = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.RESOURCES_BUTTONS, Dictionary.Button.WHITE_BOX), virtualCenterX, virtualCenterY);
        Button play = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.RESOURCES_BUTTONS, Dictionary.Button.PLAY), virtualCenterX, whiteBoxTop.getY() - (whiteBoxTop.getHeight() / 2));
        Button whiteBoxRight = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.RESOURCES_BUTTONS, Dictionary.Button.WHITE_BOX), virtualCenterX + 100, whiteBoxTop.getY() - (whiteBoxTop.getHeight() / 2));
        Button whiteBoxLeft = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.RESOURCES_BUTTONS, Dictionary.Button.WHITE_BOX), virtualCenterX - 100, whiteBoxTop.getY() - (whiteBoxTop.getHeight() / 2));
        Button whiteBoxBot = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.RESOURCES_BUTTONS, Dictionary.Button.WHITE_BOX), virtualCenterX, whiteBoxLeft.getY() - (whiteBoxLeft.getHeight() / 2));

        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mGSM.set(new PlayState(mGSM));
            }
        });

        stage.addActor(label);
        stage.addActor(whiteBoxTop);
        stage.addActor(whiteBoxBot);
        stage.addActor(whiteBoxRight);
        stage.addActor(whiteBoxLeft);
        stage.addActor(play);
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
