import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaSweepper extends JFrame {

    private JPanel panel;
    private Game game;
    private int COLS = 10;
    private int ROWS = 10;
    private final int IMAGE_SIZE = 30;

    public static void main(String[] args) {
        new JavaSweepper();
    }

    private JavaSweepper() {
        setImages();
        initMenu();
        initPanel();
        initFrame();
    }

    private void gameStart() { game = new Game(ROWS, COLS); }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu ganeMenu = new JMenu("Настройки");
        JMenu helpMenu = new JMenu("Помощь");

        menuBar.add(ganeMenu);
        menuBar.add(helpMenu);

        JMenuItem newGame = new JMenuItem("Новая игра");
        JMenuItem sizeField = new JMenuItem("Размер поля");
        JMenuItem levelGame = new JMenuItem("Уровень");
        JMenuItem exitGame = new JMenuItem("Выход");
        ganeMenu.add(newGame);
        ganeMenu.add(sizeField);
        ganeMenu.add(levelGame);
        ganeMenu.addSeparator();
        ganeMenu.add(exitGame);

        JMenuItem helpGame = new JMenuItem("Справка");
        JMenuItem aboutGame = new JMenuItem("О игре");
        helpMenu.add(helpGame);
        helpMenu.addSeparator();
        helpMenu.add(aboutGame);

        exitGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                dispose();
            }
        });

        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gameStart();
                panel.repaint();
            }
        });

        sizeField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
            }
        });
    }


//    private void setSizeField(int COLS, int ROWS) {
//        this.COLS = COLS;
//        this.ROWS = ROWS;
//    }
//
//    private void setSizeField() {
//        this.COLS = 10;
//        this.ROWS = 10;
//    }
//
//    private void initSizeField () {
//        JDialog dialog = new JDialog(this, "Размер поля", true);
//    }

//    private void showMessage(String text, String title) {
//        JOptionPane.showMessageDialog(panel, text,title, JOptionPane.INFORMATION_MESSAGE);
//    }

    private void showMessage(String text) {
        JDialog dialog = new JDialog(this);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel p = new JPanel();
        JLabel txt = new JLabel(text);
        txt.setFont(new Font("Serif", Font.BOLD, 20));
//        txt.setVerticalAlignment(JLabel.CENTER);
//        txt.setHorizontalAlignment(JLabel.CENTER);
        p.add(txt);
        JButton btnNewGame = new JButton("Сыграть еще раз");
        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameStart();
                panel.repaint();
                dialog.setVisible(false);
            }
        });
        p.add(btnNewGame);

        dialog.add(p);
        dialog.setSize(180, 180);
        dialog.setLocationRelativeTo(panel);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private void initPanel() {
        gameStart();

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Box objImage;
                for (int x = 0; x < ROWS; x++) {
                    for (int y = 0; y < COLS; y++) {
                        objImage = game.getCell(x, y).getImg();
                        g.drawImage((Image)objImage.image, y * IMAGE_SIZE, x * IMAGE_SIZE, this);
                    }
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (game.getGameStatus()) {
                    int y = e.getX() / IMAGE_SIZE;
                    int x = e.getY() / IMAGE_SIZE;

                    if (e.getButton() == MouseEvent.BUTTON1) {
                        game.openTileLeft(x, y);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        game.openTileRight(x, y);
                    }

                    if ((game.getCountCell() == game.getCountMine() && game.getCountFlag() == 0) || game.getStopGame()){
                        game.visibleAllMine();
                        game.setGameStatus(false);
                    }
                    panel.repaint();

                    if (!game.getGameStatus()) {
                        if (game.getStopGame()) {
                            showMessage("Вы проиграли!");
                        } else {
                            showMessage("Вы победили!");
                        }
                    }
                }
            }
        });



        panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(panel);
    }

    private void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Сапер");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLogo("icon");
    }

    private void setLogo(String name) {
        setIconImage(getImage(name));
    }

    private void setImages() {
        for (Box box: Box.values()) {
            box.image = getImage(box.name());
        }
    }

    private Image getImage(String name) {
        String filename = "images/" + name.toLowerCase() + ".png";
        Object obj = getClass().getResource(filename);
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}