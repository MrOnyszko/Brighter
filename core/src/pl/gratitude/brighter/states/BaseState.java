package pl.gratitude.brighter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.gratitude.brighter.Main;
import pl.gratitude.brighter.interfaces.StateInterface;
import pl.gratitude.brighter.utils.Dictionary;
import pl.gratitude.brighter.utils.GameStateManager;

/**
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public abstract class BaseState implements StateInterface {

    private static final String TAG = BaseState.class.getSimpleName();

    protected GameStateManager mGSM;
    protected Vector3 touch;

    protected BitmapFont font;
    protected SpriteBatch sb;
    protected ShapeRenderer sr;

    protected Stage stage;
    protected Viewport viewport;
    protected OrthographicCamera camera;

    protected float density;
    protected float cx;
    protected float cy;

    protected int virtualCenterX;
    protected int virtualCenterY;

    protected BaseState(GameStateManager gsm) {
        this.mGSM = gsm;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Dictionary.VIRTUAL_WIDTH, Dictionary.VIRTUAL_HEIGHT);

        viewport = new ExtendViewport(Dictionary.VIRTUAL_WIDTH, Dictionary.VIRTUAL_HEIGHT, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();

        touch = new Vector3();
        sb = new SpriteBatch();
        sr = new ShapeRenderer();

        density = Gdx.graphics.getDensity();
        cx = Main.getInstance().getScreenWidth() / 2;
        cy = Main.getInstance().getScreenHeight() / 2;

        virtualCenterX = Dictionary.VIRTUAL_WIDTH / 2;
        virtualCenterY = Dictionary.VIRTUAL_HEIGHT / 2;

        create();
    }

    public abstract void create();

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void update(float dt) {
        stage.act(dt);
    }

    @Override
    public void render() {
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        sb.dispose();
        sr.dispose();
        font.dispose();
    }
}
