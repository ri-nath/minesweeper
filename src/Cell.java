import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class Cell {

    private boolean mine, flag, reveal, checked;
    private int adjMines, row, col;
    private JButton button;

    Cell() {
        checked = false;
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

    void setChecked() {
        checked = true;
    }

    boolean getChecked() {
        return checked;
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
        flag = true;
    }

    void reveal() {
        reveal = true;
        button.setBackground(Color.green);
        if (mine) button.setBackground(Color.red);
        if (adjMines > 0) button.setText(Integer.toString(adjMines));
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
