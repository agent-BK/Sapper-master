package game;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static enums.Icons.ICON_LOGO;
import static enums.Size.SIZE_30;
import static menu.Menu.initMenu;
import static menu.Message.showMessage;

@EqualsAndHashCode(callSuper = true)
@Data
public class InitGame extends JFrame {

    private JPanel panel;
    private Game game;
    private int COLS = 10;
    private int ROWS = 10;

    public InitGame() {
        initMenu(this);
        initPanel();
        initFrame();
    }

    public void gameStart() {
        game = new Game(ROWS, COLS);
    }

    public void panelRepaint() {
        panel.repaint();
    }

    public void setSizeField(int COLS, int ROWS) {
        this.COLS = COLS;
        this.ROWS = ROWS;
    }

    public void initPanel() {
        gameStart();
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int x = 0; x < ROWS; x++) {
                    for (int y = 0; y < COLS; y++) {
                        g.drawImage(
                                game.getCell(x, y).getIcon(),
                                y * SIZE_30.getSize(),
                                x * SIZE_30.getSize(),
                                this);
                    }
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (game.isGameStatus()) {
                    int y = e.getX() / SIZE_30.getSize();
                    int x = e.getY() / SIZE_30.getSize();

                    if (e.getButton() == MouseEvent.BUTTON1) {
                        game.openTileLeft(x, y);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        game.openTileRight(x, y);
                    }

                    if ((game.getCountCell() == game.getCountMine() && game.getCountFlag() == 0) || game.isStopGame()){
                        game.visibleAllMine();
                        game.setGameStatus(false);
                    }
                    panel.repaint();

                    if (!game.isGameStatus()) {
                        if (game.isStopGame()) {
                            showMessage(InitGame.this, "Вы проиграли!");
                        } else {
                            showMessage(InitGame.this, "Вы победили!");
                        }
                    }
                }
            }
        });
        panel.setPreferredSize(
                new Dimension(COLS * SIZE_30.getSize(), ROWS * SIZE_30.getSize())
        );
        add(panel);
    }

    public void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Сапер");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(ICON_LOGO.getIcon());
    }

}