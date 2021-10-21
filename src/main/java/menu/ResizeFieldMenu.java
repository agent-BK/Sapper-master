package menu;

import enums.FieldSize;
import game.InitGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static enums.FontSize.FONT_16;
import static enums.Label.RESIZE_FIELD;
import static enums.Size.*;
import static enums.Separator.SYMBOL_X;

public class ResizeFieldMenu extends BaseMenu{

    private static JButton getBtnApply(JDialog dialog, InitGame obj, JComboBox<String> sizesList) {
        JButton btn = new JButton(RESIZE_FIELD.getText());
        btn.addActionListener(e -> {
            dialog.dispose();
            String selected = (String) sizesList.getSelectedItem();
            int size = Integer.parseInt(Objects.requireNonNull(selected).split(SYMBOL_X.getSymbol())[0]);
            obj.setSizeField(size, size);
            obj.initPanel();
            obj.initFrame();
            obj.panelRepaint();
        });
        return btn;
    }

    private static String[] getListValuesFieldSizes() {
        ArrayList<String> sizesTitle = new ArrayList<>();
        for (FieldSize fieldSize: new ArrayList<>(Arrays.asList(FieldSize.values()))) {
            sizesTitle.add(fieldSize.getSize());
        }
        return sizesTitle.toArray(new String[0]);
    }

    private static String getSelectedSize(InitGame obj) {
        int size = obj.getRows();
        String selected = null;
        for (FieldSize fieldSize: new ArrayList<>(Arrays.asList(FieldSize.values()))) {
            if (size == fieldSize.getCountSize()) {
                selected = fieldSize.getSize();
                break;
            }
        }
        return selected;
    }

    public static void initSizeField(InitGame obj, String title, String text) {
        ArrayList<Object> elements = new ArrayList<>();
        JDialog dialog = new JDialog(obj, title, true);
        JPanel panel = new JPanel();
        elements.add(getLabel(text));

        JComboBox<String> sizesList = getComboBox(
                getListValuesFieldSizes(),
                SIZE_180.getSize(),
                SIZE_25.getSize(),
                FONT_16.getSize(),
                Color.DARK_GRAY,
                SIZE_5.getSize(),
                getSelectedSize(obj)
                );

        elements.add(sizesList);
        elements.add(getBtnApply(dialog, obj, sizesList));
        setElementsMenu(panel, elements);
        setAttachmentsToDialog(dialog, panel, obj, SIZE_220.getSize());
    }

}
