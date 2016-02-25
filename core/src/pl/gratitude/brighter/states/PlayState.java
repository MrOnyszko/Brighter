package pl.gratitude.brighter.states;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

import pl.gratitude.brighter.Main;
import pl.gratitude.brighter.entities.Tile;
import pl.gratitude.brighter.utils.Dictionary;
import pl.gratitude.brighter.utils.GameStateManager;

/**
 * Created on 25.02.2016
 *
 * @author Sławomir Onyszko
 */
public class PlayState extends BaseState {

    private Timer timer;
    private Tile[][] tiles;

    private int numRows;
    private int numCols;
    private int fail;
    private int step;
    private int random;
    private int success;
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
    }

    @Override
    public void create() {

        normalLevel = 9;
        lightLevel = 8;
        step = 4;
        levelTime = 7;
        numRows = 2;
        numCols = 2;

        initBoard();
        createBoard(numRows, numCols);
        colorBoard(numRows, numCols);


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
        tileSize = Dictionary.VIRTUAL_WIDTH / numCols;
        boardHeight = tileSize * numRows;
        boardOffset = (Dictionary.VIRTUAL_HEIGHT - boardHeight) / 2; //

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
                tiles[row][col].setLight(Main.getInstance().getTilesResource().getRegion(lightLevel, "" + random));
                tiles[row][col].setNormal(Main.getInstance().getTilesResource().getRegion(normalLevel, "" + random));
            }
        }
        tiles[randomRows][randomCols].setBrighter(true);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void update(float dt) {
        super.update(dt);

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
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
