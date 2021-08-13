public class GameObject {
    private int x;
    private int y;
    private boolean isMine;
    private boolean isOpen;
    private boolean isFlag;
    private Box img;
    private int countMineNeighbors;

    public GameObject(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;

        isOpen = false;
        isFlag = false;
        countMineNeighbors = 0;
        img = Box.CLOSED;
    }

    public void incCountMineNeighbors() { countMineNeighbors++; }

    public void setCountMineNeighbors(int count) { countMineNeighbors = count; }

    public int getCountMineNeighbors() { return countMineNeighbors; }

    public boolean getMine() {
        return isMine;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setOpen(boolean status) { isOpen = status; }

    public boolean getOpen() { return isOpen; }

    public void setImg(Box img) { this.img = img; }

    public Box getImg() { return img; }

    public void setFlag(boolean flag) { isFlag = flag; }

    public boolean getFlag() { return isFlag; }
}
