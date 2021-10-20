package game;

import enums.Icons;
import lombok.Data;

import java.awt.*;

@Data
public class GameObject {

    int x;
    int y;
    boolean isMine;
    boolean isOpen;
    boolean isFlag;
    Icons img;
    int countMineNeighbors;

    public GameObject(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
        this.isOpen = false;
        this.isFlag = false;
        this.countMineNeighbors = 0;
        this.img = Icons.CLOSED;
    }

    public Image getIcon() {
        return img.getIcon();
    }

    public void incCountMineNeighbors() {
        countMineNeighbors++;
    }

}
