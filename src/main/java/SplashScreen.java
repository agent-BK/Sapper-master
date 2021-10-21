import javax.swing.*;
import java.awt.*;

import static enums.FontSize.FONT_12;
import static enums.Image.INTRO;
import static enums.Label.*;
import static enums.Size.*;

public class SplashScreen extends JWindow {

    private final int duration;

    private final int WIDTH = SIZE_480.getSize();
    private final int HEIGHT =SIZE_320.getSize();
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

    public SplashScreen(int d) {
        duration = d;
    }

    public void showSplash() {
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        setBounds((SCREEN.width - WIDTH) / 2, (SCREEN.height - HEIGHT) / 2, WIDTH, HEIGHT);
        JLabel label = new JLabel(INTRO.getImage());
        JLabel author = new JLabel(String.format(AUTHOR.getText(), YEAR.getText(), VERSION.getText()), JLabel.CENTER);
        author.setFont(new Font(Font.SERIF, Font.BOLD, FONT_12.getSize()));
        content.add(label, BorderLayout.CENTER);
        content.add(author, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, SIZE_5.getSize()));
        setVisible(true);
        try {
            Thread.sleep(duration);
            dispose();
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
