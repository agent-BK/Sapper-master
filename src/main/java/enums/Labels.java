package enums;

public enum Labels {

    REPLAY_GAME("Сыграть еще раз"),
    RESIZE_FIELD("Изменить размер поля"),
    AUTHOR("Author: Andrey Voitovich, 2021 version: 1.0"),
    TITLE("Сапер"),
    LOSE("Вы проиграли!"),
    WIN("Вы победили!");

    private final String text;

    Labels(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
