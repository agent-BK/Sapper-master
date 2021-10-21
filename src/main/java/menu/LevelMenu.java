package menu;

import enums.Level;
import game.InitGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static enums.Label.SET_LEVEL;
import static enums.Size.*;

public class LevelMenu extends BaseMenu{

    private static ButtonGroup setRadioButton(ArrayList<Object> elements, int levelGame) {
        ButtonGroup btnGroup = new ButtonGroup();

        ArrayList<Level> levels = new ArrayList<>(Arrays.asList(Level.values()));
        for (Level level: levels) {
            String btnLevelNum = level.getLevelsNum();
            JRadioButton btn;
            if (Integer.toString(levelGame).equals(btnLevelNum) ) {
                btn = new JRadioButton(level.getLevelName(), true);
            } else {
                btn = new JRadioButton(level.getLevelName(), false);
            }
            btn.setActionCommand(level.getLevelsNum());
            btn.setPreferredSize(
                    new Dimension(SIZE_180.getSize(), SIZE_25.getSize())
            );
            btnGroup.add(btn);
            elements.add(btn);

        }
        return btnGroup;

    }

    private static JButton getBtnApply(JDialog dialog, InitGame obj, ButtonGroup btnGroup) {
        JButton btn = new JButton(SET_LEVEL.getText());
        btn.addActionListener(e -> {
            dialog.dispose();
            obj.setLevel(Integer.parseInt(btnGroup.getSelection().getActionCommand()));
            obj.initPanel();
            obj.initFrame();
            obj.panelRepaint();
        });
        return btn;
    }

    public static void initLevelMenu(InitGame obj, String title, String text) {
        ArrayList<Object> elements = new ArrayList<>();
        JDialog dialog = new JDialog(obj, title, true);
        JPanel panel = new JPanel();
        elements.add(getLabel(text));
        ButtonGroup btnGroup = setRadioButton(elements, obj.getLevel());
        elements.add(getBtnApply(dialog, obj, btnGroup));
        setElementsMenu(panel, elements);
        setAttachmentsToDialog(dialog, panel, obj, SIZE_220.getSize());
    }
}
