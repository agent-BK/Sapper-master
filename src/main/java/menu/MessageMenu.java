package menu;

import game.InitGame;

import javax.swing.*;
import java.util.ArrayList;

import static enums.Label.REPLAY_GAME;
import static enums.Size.SIZE_180;

public class MessageMenu extends BaseMenu {

    private static JButton getBtnNewGame(JDialog dialog, InitGame obj) {
        JButton btn = new JButton(REPLAY_GAME.getText());
        btn.addActionListener(e -> {
            obj.gameStart();
            obj.panelRepaint();
            dialog.setVisible(false);
        });
        return btn;
    }

    public static void showMessage(InitGame obj, String text) {
        ArrayList<Object> elements = new ArrayList<>();
        JDialog dialog = new JDialog(obj);
        JPanel panel = new JPanel();
        elements.add(getLabel(text));
        elements.add(getBtnNewGame(dialog, obj));
        setElementsMenu(panel, elements);
        setAttachmentsToDialog(dialog, panel, obj, SIZE_180.getSize());
    }

}
