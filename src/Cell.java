import javax.swing.*;
import java.awt.*;

class Cell {

    private boolean mine, flag, reveal;
    private int bombs;
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
    }

    void findBombs(int bombs) {
        this.bombs = bombs;
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
    }

    void flag() {
        flag = true;
    }

    void reveal() {
        reveal = true;
    }

    void position(int[] position) {
        this.position = position;
    }

    int[] getPosition() {
        return position;
    }

     JButton render() {
        if (mine) button.setBackground(Color.red);
        if (flag) button.setBackground(Color.red);
        if (reveal) {
            button.setBackground(Color.green);
            if (bombs > 0) {
                button.setText(Integer.toString(bombs));
            }
        }
        return button;
     };

}
