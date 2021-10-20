package enums;

public enum Labels {

    REPLAY_GAME("Сыграть еще раз"),
    RESIZE_FIELD("Изменить размер поля");

    private final String text;

    Labels(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
