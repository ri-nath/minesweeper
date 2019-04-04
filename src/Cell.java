import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class Cell {

    private boolean mine, flag, reveal;
    private int adjMines, row, col;
    private ImageIcon mineIcon, flagIcon;
    private JButton button;

    Cell() {
        mine = false;
        flag = false;
        reveal = false;

        mineIcon = loadImageIcon("resources/mine.jpg", 20);
        flagIcon = loadImageIcon("resources/flag.jpg", 20);

        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setBackground(Color.gray);
        button.setBorder(new LineBorder(Color.darkGray));
    }

    private ImageIcon loadImageIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage();
        image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        return icon;
    }

    void setAdjMines(int mines) {
        adjMines = mines;
    }

    int getAdjMines() {
        return adjMines;
    }

    boolean checkMine() {
        return mine;
    }

    boolean getFlag() {
        return flag;
    }

    boolean getReveal() {
        return reveal;
    }

    void addMine() {
        mine = true;
    }

    void flag() {
        button.setIcon(flagIcon);
        flag = true;
    }

    void reveal() {
        reveal = true;
        button.setBackground(Color.lightGray);
        if (mine) {
            button.setIcon(mineIcon);
        } else {
            button.setIcon(null);
            if (adjMines > 0) {
                button.setText(Integer.toString(adjMines));
            }
        }
    }

    void position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

     JButton render() {
        return button;
     }

}
