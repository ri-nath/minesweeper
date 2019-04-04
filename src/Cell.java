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



        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setBackground(Color.gray);
        button.setBorder(new LineBorder(Color.darkGray));
    }

    void addIcon(ImageIcon flag, ImageIcon mine) {
        mineIcon = mine;
        flagIcon = flag;
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

    void addMine() {
        mine = true;
    }

    void removeMine() {
        mine = false;
    }

    boolean getReveal() {
        return reveal;
    }

    void flag() {
        if (!reveal) {
            if (flag) {
                flag = false;
                button.setIcon(null);
            } else {
                button.setIcon(flagIcon);
                flag = true;
            }
        }
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
