package game;

import enums.Icons;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Game {

    int COLS;
    int ROWS;
    int countMine;
    int countCell;
    int countFlag;
    int level;
    boolean stopGame;
    boolean gameStatus;
    GameObject[][] gamePanel;

    public Game(int ROWS, int COLS, int level) {
        this.ROWS = ROWS;
        this.COLS = COLS;
        this.countMine = 0;
        this.countFlag = 0;
        this.level = level;
        this.gamePanel = new GameObject[ROWS][COLS];
        this.countCell = ROWS * COLS;
        this.stopGame = false;
        this.gameStatus = true;
        initField ();
    }

    /** образование поля **/
    private void initField () {
        countMine = 0;
        boolean mine_status;
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                mine_status = getRandomNumber(level) == 1;
                gamePanel[x][y] = new GameObject(x, y, mine_status);
                if (mine_status) countMine++;
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
                if (!gameObject.isMine()) {
                    List<GameObject> lists = getNeighbors(gameObject);
                    for(GameObject list : lists) {
                        if (list.isMine()) {
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

    /** Открываем все мины **/
    public void visibleAllMine() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                GameObject obj = gamePanel[x][y];
                if (obj.isFlag()) {
                    if (obj.isMine()) {
                        obj.setImg(Icons.BOMB);
                    } else {
                        obj.setImg(Icons.NOBOMB);
                    }
                    obj.setOpen(true);
                } else if (obj.isMine() && !obj.isOpen()) {
                    obj.setImg(Icons.BOMBED);
                    obj.setOpen(true);
                }
            }
        }
    }

    /** левое нажатие мышки **/
    public void openTileLeft(int x, int y) {
        GameObject obj = gamePanel[x][y];
        if (!obj.isOpen() && !obj.isFlag() && !stopGame) {
            obj.setOpen(true);

            if (obj.isMine()) {
                obj.setImg(Icons.BOMBED);
                stopGame = true;
            } else {
                countCell--;
                if (obj.getCountMineNeighbors() > 0) {
                    obj.setImg(Icons.values()[obj.getCountMineNeighbors()]);
                } else {
                    obj.setImg(Icons.ZERO);
                    List<GameObject> result = getNeighbors(obj);
                    for (GameObject cell : result) {
                        if (!cell.isOpen()) {
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
            if (!obj.isOpen() && !obj.isFlag() && countFlag > 0) {
                obj.setFlag(true);
                obj.setImg(Icons.FLAGED);
                countFlag--;
            } else if (!obj.isOpen() && obj.isFlag()) {
                obj.setFlag(false);
                obj.setImg(Icons.CLOSED);
                countFlag++;
            }
        }
    }

    public GameObject getCell(int x, int y) {
        return gamePanel[x][y];
    }

}