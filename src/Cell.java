import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class Cell {

    private boolean mine, flag, reveal;
    private int bombs, row, col;
    private int[] position;
    private JButton button;

    Cell() {
        mine = false;
        flag = false;
        reveal = false;

        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setBackground(Color.lightGray);
        button.setBorder(new LineBorder(Color.gray));
    }

    void setBombs(int bombs) {
        this.bombs = bombs;
    }

    int getBombs() {
        return bombs;
    }

    boolean getMine() {
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
        button.setBackground(Color.red);
    }

    void flag() {
        flag = true;
    }

    void reveal() {
        reveal = true;
        button.setBackground(Color.green);
        if (bombs > 0) button.setText(Integer.toString(bombs));
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
     };

}
