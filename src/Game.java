import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final int COLS;
    private final int ROWS;
    private int countMine;
    private int countCell;
    private int countFlag;
    private boolean stopGame;
    private boolean gameStatus;
    private GameObject[][] gamePanel;

    public Game(int ROWS, int COLS) {
        this.ROWS = ROWS;
        this.COLS = COLS;

        countMine = 0;
        countFlag = 0;
        gamePanel = new GameObject[ROWS][COLS];
        countCell = ROWS * COLS;
        stopGame = false;
        gameStatus = true;

        initField ();
    }

    /** образование поля **/
    private void initField () {
        countMine = 0;
        boolean mineStatus = false;
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                mineStatus = getRandomNumber(10) == 1;
                gamePanel[x][y] = new GameObject(x, y, mineStatus);
                if (mineStatus) countMine++;
            }
        }
        countFlag = countMine;
        initCountMine();
    }

    /** подсчет количества мин соседей **/
    private void initCountMine() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                GameObject gameObject = gamePanel[x][y];
                if (!gameObject.getMine()) {
                    List<GameObject> lists = getNeighbors(gameObject);
                    for(GameObject list : lists) {
                        if (list.getMine()) {
                            gameObject.incCountMineNeighbors();
                        }
                    }
                }
            }
        }

    }

    /** список соседних ячеек **/
    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int x = gameObject.getX() - 1; x <= gameObject.getX() + 1 ; x++) {
            for (int y = gameObject.getY() - 1; y <= gameObject.getY() + 1 ; y++) {
                if (x < 0 || x >= ROWS || y < 0 || y >= COLS) continue;
                if (gamePanel[x][y] == gameObject) continue;
                result.add(gamePanel[x][y]);
            }
        }
        return result;
    }

    /** случайное число для расположения мин **/
    private int getRandomNumber(int num) {
        Random random = new Random();
        return random.nextInt(num);
    }

    /** public **/
    /** Открываем все мины **/
    public void visibleAllMine() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                GameObject obj = gamePanel[x][y];
                if (obj.getFlag()) {
                    if (obj.getMine()) {
                        obj.setImg(Box.BOMB);
                    } else {
                        obj.setImg(Box.NOBOMB);
                    }
                    obj.setOpen(true);
                } else if (obj.getMine() && !obj.getOpen()) {
                    obj.setImg(Box.BOMBED);
                    obj.setOpen(true);
                }
            }
        }
    }

    /** левое нажатие мышки **/
    public void openTileLeft(int x, int y) {
        GameObject obj = gamePanel[x][y];
        if (!obj.getOpen() && !obj.getFlag() && !stopGame) {
            obj.setOpen(true);

            if (obj.getMine()) {
                obj.setImg(Box.BOMBED);
                stopGame = true;
            } else {
                countCell--;
                if (obj.getCountMineNeighbors() > 0) {
                    obj.setImg(Box.values()[obj.getCountMineNeighbors()]);
                } else {
                    obj.setImg(Box.ZERO);
                    List<GameObject> result = getNeighbors(obj);
                    for (GameObject cell : result) {
                        if (!cell.getOpen()) {
                            openTileLeft(cell.getX(), cell.getY());
                        }
                    }
                }
            }
        }
    }

    /** правое нажатие мышки **/
    public void openTileRight(int x, int y) {
        GameObject obj = gamePanel[x][y];
        if (!stopGame) {
            if (!obj.getOpen() && !obj.getFlag() && countFlag > 0) {
                obj.setFlag(true);
                obj.setImg(Box.FLAGED);
                countFlag--;
            } else if (!obj.getOpen() && obj.getFlag()) {
                obj.setFlag(false);
                obj.setImg(Box.CLOSED);
                countFlag++;
            }
        }
    }

    public GameObject getCell(int x, int y) { return gamePanel[x][y]; }

    public boolean getStopGame() { return stopGame; }

    public int getCountMine() { return countMine; }

    public int getCountCell() { return countCell; }

    public int getCountFlag() { return countFlag; }

    public boolean getGameStatus() { return gameStatus; }

    public void setGameStatus(boolean status) { gameStatus = status; }
}