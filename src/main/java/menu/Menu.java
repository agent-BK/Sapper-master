package menu;

import game.InitGame;

import javax.swing.*;

import static enums.TextMenu.*;
import static menu.ResizeField.initSizeField;

public class Menu {

    public static void initMenu(InitGame obj) {
        JMenuBar menuBar = new JMenuBar();
        obj.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu(SETTINGS.getText());
        JMenu helpMenu = new JMenu(HELP.getText());

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        JMenuItem newGame = new JMenuItem(NEW_GAME.getText());
        JMenuItem sizeField = new JMenuItem(FIELD_SIZE.getText());
        JMenuItem levelGame = new JMenuItem(LEVEL.getText());
        JMenuItem exitGame = new JMenuItem(EXIT.getText());
        gameMenu.add(newGame);
        gameMenu.add(sizeField);
        gameMenu.add(levelGame);
        gameMenu.addSeparator();
        gameMenu.add(exitGame);

        JMenuItem helpGame = new JMenuItem(INFORMATION.getText());
        JMenuItem aboutGame = new JMenuItem(ABOUT.getText());
        helpMenu.add(helpGame);
        helpMenu.addSeparator();
        helpMenu.add(aboutGame);

        exitGame.addActionListener(arg0 -> {
            obj.setVisible(false);
            obj.dispose();
        });

        newGame.addActionListener(arg0 -> {
            obj.gameStart();
            obj.panelRepaint();
        });

        sizeField.addActionListener(arg0 -> initSizeField(obj, "Размер поля", "Размер поля"));
    }

}
