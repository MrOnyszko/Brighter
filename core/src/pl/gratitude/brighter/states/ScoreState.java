package pl.gratitude.brighter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private static final String TAG = ScoreState.class.getSimpleName();

    private Preferences preferences;

    private Label scoresLabel;
    private Label highScoresLabel;

    private int currentScore;
    private int currentHighScore;

    protected ScoreState(GameStateManager gsm) {
        super(gsm);
        create();
    }

    private void create() {

        preferences = Gdx.app.getPreferences(Dictionary.Preferences.HIGH_SCORE_HANDLER);

        Label title = createLabel(Dictionary.Labels.SCORES_TITLE, 50, virtualCenterX, Dictionary.Dimensions.VIRTUAL_HEIGHT - 230, Color.WHITE);
        Label newScoreLabel = createLabel(Dictionary.Labels.NOW, 24, virtualCenterX - 100, title.getY() - 30, Color.WHITE);
        Label bestScoreLabel = createLabel(Dictionary.Labels.BEST, 24, virtualCenterX + 100, title.getY() - 30, Color.WHITE);
        scoresLabel = createLabel(String.valueOf(currentScore), 24, virtualCenterX - 100, title.getY() - 110, Color.WHITE);
        highScoresLabel = createLabel(String.valueOf(currentHighScore), 24, virtualCenterX + 100, title.getY() - 110, Color.WHITE);
        Label companyName = createLabel(Dictionary.Labels.GRATITUDE, 12, virtualCenterX, 30, Color.WHITE);
        Button whiteBoxTop = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.Resources.RESOURCES_BUTTONS, Dictionary.Button.WHITE_BOX), virtualCenterX, title.getY() - 100);
        Button playButton = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.Resources.RESOURCES_BUTTONS, Dictionary.Button.RELOAD), virtualCenterX, whiteBoxTop.getY() - (whiteBoxTop.getHeight() / 2));
        Button whiteBoxRight = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.Resources.RESOURCES_BUTTONS, Dictionary.Button.WHITE_BOX), virtualCenterX + 100, whiteBoxTop.getY() - (whiteBoxTop.getHeight() / 2));
        Button whiteBoxLeft = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.Resources.RESOURCES_BUTTONS, Dictionary.Button.WHITE_BOX), virtualCenterX - 100, whiteBoxTop.getY() - (whiteBoxTop.getHeight() / 2));

        Button back = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.Resources.RESOURCES_BUTTONS, Dictionary.Button.BACK), virtualCenterX, whiteBoxLeft.getY() - (whiteBoxLeft.getHeight() / 2));

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mGSM.set(new PlayState(mGSM));
            }
        });
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mGSM.set(new MenuState(mGSM));
            }
        });

        saveHighScore();

        stage.addActor(whiteBoxTop);
        stage.addActor(playButton);
        stage.addActor(whiteBoxRight);
        stage.addActor(whiteBoxLeft);
        stage.addActor(title);
        stage.addActor(scoresLabel);
        stage.addActor(highScoresLabel);
        stage.addActor(companyName);
        stage.addActor(newScoreLabel);
        stage.addActor(bestScoreLabel);
        stage.addActor(back);

    }

    private void saveHighScore() {
        currentHighScore = preferences.getInteger(Dictionary.Preferences.KEY_HIGH_SCORE, 0);
        if (currentScore > currentHighScore) {
            preferences.putInteger(Dictionary.Preferences.KEY_HIGH_SCORE, currentScore);
            preferences.flush();
            highScoresLabel.setColor(Color.GREEN);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        scoresLabel.setText(String.valueOf(currentScore));
        highScoresLabel.setText(String.valueOf(preferences.getInteger(Dictionary.Preferences.KEY_HIGH_SCORE, 0)));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
