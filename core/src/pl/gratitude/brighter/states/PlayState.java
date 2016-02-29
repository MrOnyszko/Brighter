package pl.gratitude.brighter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;

import pl.gratitude.brighter.Main;
import pl.gratitude.brighter.entities.Tile;
import pl.gratitude.brighter.gui.Button;
import pl.gratitude.brighter.utils.Dictionary;
import pl.gratitude.brighter.utils.GameStateManager;
import pl.gratitude.brighter.utils.Util;

/**
 * Created on 25.02.2016
 *
 * @author Sławomir Onyszko
 */
public class PlayState extends BaseState {

    private static final String TAG = PlayState.class.getSimpleName();

    private Timer.Task timerTask;
    private Tile[][] tiles;

    private Label timerLabel;
    private Label failLabel;
    private Label diamondsLabel;

    private final int maxElements = 10;
    private final int normalMaxLevels = 6;
    private final int lightMaxLevels = normalMaxLevels - 1;

    private int numRows;
    private int numCols;
    private int failsCount;
    private int step;
    private int random;
    private int diamondsCount;
    private int tileSize;
    private int levelTime;
    private int lightLevel;
    private int randomRows;
    private int randomCols;
    private int normalLevel;
    private int boardHeight;

    private float boardOffset;
    private boolean stopTimer;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        create();
    }

    private void create() {

        normalLevel = 9;
        lightLevel = 8;
        step = 4;
        levelTime = 7;
        numRows = 2;
        numCols = 2;


        timerLabel = createLabel(Dictionary.Labels.TIMER + levelTime, 18, virtualCenterX, Dictionary.Dimensions.VIRTUAL_HEIGHT - 100, Color.WHITE);
        diamondsLabel = createLabel(String.valueOf(diamondsCount), 18, Dictionary.Dimensions.VIRTUAL_WIDTH - 40, Dictionary.Dimensions.VIRTUAL_HEIGHT - 100, Color.WHITE);
        failLabel = createLabel(String.valueOf(failsCount), 18, 40, Dictionary.Dimensions.VIRTUAL_HEIGHT - 100, Color.WHITE);
        Label info = createLabel(Dictionary.Labels.INFO, 16, virtualCenterX, 40, Color.WHITE);


        Button timerIco = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.Resources.RESOURCES_ICONS, Dictionary.Icon.TIMER), virtualCenterX, Dictionary.Dimensions.VIRTUAL_HEIGHT - 40);
        Button diamondIco = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.Resources.RESOURCES_ICONS, Dictionary.Icon.DIAMOND), Dictionary.Dimensions.VIRTUAL_WIDTH - 40, Dictionary.Dimensions.VIRTUAL_HEIGHT - 40);
        Button failIco = new Button(Main.getInstance().getUserInterfaceResource().getSprite(Dictionary.Resources.RESOURCES_ICONS, Dictionary.Icon.FAIL), 40, Dictionary.Dimensions.VIRTUAL_HEIGHT - 40);

        initBoard();
        createBoard(numRows, numCols);
        colorBoard(numRows, numCols);

        timerTask = new Timer.Task() {
            @Override
            public void run() {
                if (stopTimer) levelTime--;
            }
        };
        Timer.schedule(timerTask, 1, 1 / 2f);


        stage.addActor(timerIco);
        stage.addActor(diamondIco);
        stage.addActor(failIco);

        stage.addActor(info);
        stage.addActor(timerLabel);
        stage.addActor(diamondsLabel);
        stage.addActor(failLabel);

    }

    private void initBoard() {
        tiles = new Tile[11][11];
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                tiles[row][col] = new Tile(
                        col * tileSize + tileSize / 2,
                        row * tileSize + boardOffset + tileSize / 2,
                        tileSize,
                        tileSize
                );
            }
        }
    }

    private void createBoard(int numRows, int numCols) {
        tileSize = Dictionary.Dimensions.VIRTUAL_WIDTH / numCols;
        boardHeight = tileSize * numRows;
        boardOffset = (Dictionary.Dimensions.VIRTUAL_HEIGHT - boardHeight) / 2;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                tiles[row][col].setX(col * tileSize + tileSize / 2);
                tiles[row][col].setY(row * tileSize + boardOffset + tileSize / 2);
                tiles[row][col].setWidth(tileSize);
                tiles[row][col].setHeight(tileSize);
                tiles[row][col].setBrighter(false);
            }
        }
    }

    private void colorBoard(int numRows, int numCols) {
        random = MathUtils.random(1, 16);
        randomRows = MathUtils.random(numRows - 1);
        randomCols = MathUtils.random(numCols - 1);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                tiles[row][col].setLight(Main.getInstance().getTilesResource().getRegion(lightLevel, String.valueOf(random)));
                tiles[row][col].setNormal(Main.getInstance().getTilesResource().getRegion(normalLevel, String.valueOf(random)));
            }
        }
        tiles[randomRows][randomCols].setBrighter(true);
    }

    private void handleUserActions() {
        if (Gdx.input.justTouched()) {
            touch.x = Gdx.input.getX();
            touch.y = Gdx.input.getY();
            camera.unproject(touch);

            if (touch.x > 0 && touch.x < Dictionary.Dimensions.VIRTUAL_WIDTH && touch.y > boardOffset && touch.y < boardOffset + boardHeight) {
                int row = (int) ((touch.y - boardOffset) / tileSize);
                int col = (int) (touch.x / tileSize);

                if (tiles[row][col].isBrighter()) {
                    diamondsCount++;
                    stopTimer = true;

                    if ((diamondsCount & (step - 1)) == 0) {
                        numRows++;
                        numCols++;
                    }

                    switch (diamondsCount) {
                        case 4:
                            normalLevel--;
                            lightLevel--;
                            step = 8;
                            break;
                        case 8:
                            normalLevel--;
                            lightLevel--;
                            step = 12;
                            break;
                        case 16:
                            if (normalLevel <= normalMaxLevels && lightLevel <= lightMaxLevels) {
                                normalLevel = normalMaxLevels;
                                lightLevel = lightMaxLevels;
                            }
                            step = 14;
                            break;
                    }


                    if (numRows >= maxElements || numCols >= maxElements) {
                        numRows = numCols = maxElements;
                    }

                    levelTime = 7;
                    diamondsLabel.setText(String.valueOf(diamondsCount));
                } else {
                    failsCount++;
                    failLabel.setText(String.valueOf(failsCount));
                    stopTimer = true;
                }
                createBoard(numRows, numCols);
                colorBoard(numRows, numCols);
            }
        }
    }

    private void done() {
        ScoreState scoreState = new ScoreState(mGSM);
        scoreState.setCurrentScore(diamondsCount);
        mGSM.set(scoreState);
        tiles = null;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        handleUserActions();

        if (levelTime <= 0 || failsCount > 3) {
            done();
        }

        timerLabel.setText(Dictionary.Labels.TIMER + String.valueOf(levelTime));
        if (levelTime <= 3) {
            timerLabel.setColor(Util.hexToRGBA("#c62828FF")); // Ustaw kolor etykiety licznika na czerwony.
        } else {
            timerLabel.setColor(Color.WHITE);
        }
    }

    @Override
    public void render() {
        super.render();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                tiles[row][col].render(sb);
            }
        }
        sb.end();

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
