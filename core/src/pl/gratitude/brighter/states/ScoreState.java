package pl.gratitude.brighter.states;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.gratitude.brighter.Main;
import pl.gratitude.brighter.gui.Button;
import pl.gratitude.brighter.utils.Dictionary;
import pl.gratitude.brighter.utils.GameStateManager;

/**
 * Created on 25.02.2016
 *
 * @author Sławomir Onyszko
 */
public class ScoreState extends BaseState {

    private Button button;

    protected ScoreState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void create() {
        button = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.RESOURCES_BUTTONS, Dictionary.Button.BACK), virtualCenterX, virtualCenterY);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mGSM.set(new MenuState(mGSM));
            }
        });

        stage.addActor(button);
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
