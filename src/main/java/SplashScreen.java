import javax.swing.*;
import java.awt.*;

import static enums.FontSize.FONT_12;
import static enums.Size.*;
import static utils.FileUtils.getImageIcon;

public class SplashScreen extends JWindow {

    private final int duration;

    private final int WIDTH = SIZE_480.getSize();
    private final int HEIGHT =SIZE_320.getSize();
    private final String AUTHOR = "Author: Andrey Voitovich, 2021 version: 1.0";
    private final String PATH_INTRO_IMAGE = "images/intro.jpg";
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

    public SplashScreen(int d) {
        duration = d;
    }

    public void showSplash() {
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);

        setBounds(
                (SCREEN.width - WIDTH) / 2,
                (SCREEN.height - HEIGHT) / 2,
                WIDTH, HEIGHT);

        JLabel label = new JLabel(getImageIcon(PATH_INTRO_IMAGE));
        JLabel author = new JLabel(AUTHOR, JLabel.CENTER);
        author.setFont(new Font(Font.SERIF, Font.BOLD, FONT_12.getSize()));
        content.add(label, BorderLayout.CENTER);
        content.add(author, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, SIZE_5.getSize()));
        setVisible(true);

        try {
            Thread.sleep(duration);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(false);
    }

    public void showSplashAndExit() {
        showSplash();
    }

}
