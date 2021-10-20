package menu;

import game.InitGame;
import utils.DigitFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;

import static enums.FontSize.FONT_20;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public abstract class BaseMenu {

    protected static JLabel setLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.SERIF, Font.BOLD, FONT_20.getSize()));
        return label;
    }

    protected static JTextField getInputInteger(String placeHolder, int size) {
        JTextField field = new JTextField(placeHolder, size);
        PlainDocument doc = (PlainDocument) field.getDocument();
        doc.setDocumentFilter(new DigitFilter());
        return field;
    }

    protected static int getInnerInput(JTextField field) {
        return Integer.parseInt(field.getText());
    }

    protected static void setAttachmentsToDialog(JDialog dialog, JPanel panel, InitGame obj, int size) {
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.add(panel);
        dialog.setSize(size, size);
        dialog.setLocationRelativeTo(obj.getPanel());
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    protected static void setElementsMenu(JPanel panel, ArrayList<Object> elements) {
        for (Object element: elements) {
            panel.add((Component) element);
        }
    }
}
