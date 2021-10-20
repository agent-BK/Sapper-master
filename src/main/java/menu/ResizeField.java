package menu;

import game.InitGame;

import javax.swing.*;
import java.util.ArrayList;

import static enums.Labels.RESIZE_FIELD;
import static enums.Size.SIZE_10;
import static enums.Size.SIZE_220;

public class ResizeField extends BaseMenu{

    private static JButton getBtnApply(JDialog dialog, InitGame obj, JTextField fieldCols, JTextField fieldRows) {
        JButton btn = new JButton(RESIZE_FIELD.getText());
        btn.addActionListener(e -> {
            dialog.dispose();
            obj.setSizeField(getInnerInput(fieldRows), getInnerInput(fieldCols));
            obj.initPanel();
            obj.initFrame();
            obj.panelRepaint();
        });
        return btn;
    }

    public static void initSizeField(InitGame obj, String title, String text) {
        ArrayList<Object> elements = new ArrayList<>();
        JDialog dialog = new JDialog(obj, title, true);
        JPanel panel = new JPanel();
        elements.add(setLabel(text));
        JTextField fieldCols = getInputInteger(Integer.toString(obj.getCOLS()), SIZE_10.getSize());
        JTextField fieldRows = getInputInteger(Integer.toString(obj.getROWS()), SIZE_10.getSize());
        elements.add(fieldCols);
        elements.add(fieldRows);
        elements.add(getBtnApply(dialog, obj, fieldCols, fieldRows));
        setElementsMenu(panel, elements);
        setAttachmentsToDialog(dialog, panel, obj, SIZE_220.getSize());
    }

}
