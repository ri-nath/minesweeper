import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class Board implements ActionListener {

    private int height, width;

    private Cell[][] minefield;

    private JFrame frame;


    Board(int height, int width) {
        frame = new JFrame("minesweeper");

        this.height = height;
        this.width = width;

        minefield = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = new Cell();
                if (new Random().nextInt(3)==0) cell.addMine();
                cell.position(row, col);
                minefield[row][col] = cell;
            }
        }

        for (Cell[] row : minefield) {
            for (Cell cell : row) {
                findBombs(cell);

                JButton button = cell.render();
                button.addActionListener(this);
                button.putClientProperty("cell", cell);
                frame.add(button);
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(height, width));
        frame.pack();
        frame.setVisible(true);
    }

    private void findBombs(Cell target){
        int bombs = 0;
        int row = target.getRow();
        int col = target.getCol();

        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
                if (isValid(row+r, col+c)) if (minefield[row+r][col+c].getMine()) {
                    bombs++;
                }
            }
        }

        target.setBombs(bombs);
    };

    private void chainReveal(Cell target){
        ArrayList<Cell> cells = new ArrayList<>();
        int row = target.getRow();
        int col = target.getCol();

        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
                if (isValid(row+r, col+c) && r != c) {
                    if (!minefield[row+r][col+c].getMine()) {
                        minefield[row+r][col+c].reveal();
                    }
                    if (minefield[row+r][col+c].getBombs() == 0) {
                        cells.add(minefield[row+r][col+c]);
                    }
                }
            }
        }

        for (Cell cell : cells) {
            chainReveal(cell);
        }

        target.reveal();

        frame.pack();
    };

    private boolean isValid(int row, int col){
        return (row >= 0) && (row < height) &&
                (col >= 0) && (col < width);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Cell cell = (Cell) ((JButton) e.getSource()).getClientProperty("cell");
        chainReveal(cell);
    }
}
