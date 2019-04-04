import javax.swing.*;
import java.awt.*;

public class Menu {
    private JFrame menu;
    private JButton start;
    private JLabel height, width;
    private JTextField heightInput, widthInput;

    Menu() {
        menu = new JFrame("minesweeper");
        menu.setLayout(new GridLayout(3, 2));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        height = new JLabel("Height:");
        heightInput = new JTextField("10");

        width = new JLabel("Width:");
        widthInput = new JTextField("10");

        start = new JButton("Start Game");
        start.addActionListener(event -> new Board(Integer.parseInt(heightInput.getText()), Integer.parseInt(widthInput.getText())));

        menu.add(height);
        menu.add(heightInput);
        menu.add(width);
        menu.add(widthInput);
        menu.add(start);
        menu.setVisible(true);
        menu.pack();
    }
}
