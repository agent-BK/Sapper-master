package enums;

public enum FontSize {

    FONT_20(20),
    FONT_12(12);

    private final int size;

    FontSize(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }

}
