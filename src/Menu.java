import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;

class Menu {
    private JFrame menu;
    private JButton start;
    private JComboBox<String> difficultyInput;
    private JLabel height, width, difficulty;
    private JTextField heightInput, widthInput;
    private HashMap<String, Integer> difficulties;

    Menu() {
        difficulties = new HashMap<>();
        difficulties.put("Easy", 25);
        difficulties.put("Medium", 20);
        difficulties.put("Hard", 15);
        difficulties.put("Impossible", 5);

        menu = new JFrame("minesweeper");

        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);

        height = new JLabel("Height:");
        height.setBorder(padding);
        heightInput = new JTextField("10");
        heightInput.setBorder(padding);

        width = new JLabel("Width:");
        width.setBorder(padding);
        widthInput = new JTextField("10");
        widthInput.setBorder(padding);

        String[] diffString = {"Easy", "Medium", "Hard", "Impossible"};

        difficulty = new JLabel("Difficulty:");
        difficulty.setBorder(padding);
        difficultyInput = new JComboBox<>(diffString);
        difficultyInput.setBorder(padding);

        start = new JButton("Start Game");
        start.setBorder(padding);
        start.addActionListener(event -> new Board(Integer.parseInt(heightInput.getText()),
                Integer.parseInt(widthInput.getText()),
                difficulties.get(difficultyInput.getSelectedItem())));

        menu.setLayout(new GridLayout(4, 2));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu.add(height);
        menu.add(heightInput);
        menu.add(width);
        menu.add(widthInput);
        menu.add(difficulty);
        menu.add(difficultyInput);
        menu.add(start);
        menu.setVisible(true);
        menu.pack();
    }
}
