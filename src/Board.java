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
        this.height = height;
        this.width = width;

        frame = new JFrame("minesweeper");
        minefield = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = new Cell();
                if (new Random().nextInt(5)==0) cell.addMine();
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
                if (validate(row+r, col+c)) if (minefield[row+r][col+c].checkMine()) {
                    bombs++;
                }
            }
        }

        target.setAdjMines(bombs);
    };

    private void chainReveal(Cell origin){
        if (origin.getReveal()) return;
        if (origin.getAdjMines() > 0) {
            origin.reveal();
            return;
        }

        int row = origin.getRow();
        int col = origin.getCol();
        ArrayList<Cell> cells = new ArrayList<>();

        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
                if (validate(r+row, c+col)) {
                    if (!minefield[r+row][c+col].getReveal()) {
                        if (minefield[r+row][c+col].getAdjMines() > 0) {
                            minefield[r+row][c+col].reveal();
                        } else {
                            cells.add(minefield[r+row][c+col]);
                        }
                    }
                }
            }
        }

        origin.reveal();

        for (Cell cell : cells) {
            chainReveal(cell);
        }
    };


    private boolean validate(int row, int col){
        return (row >= 0) && (row < height) &&
                (col >= 0) && (col < width);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Cell cell = (Cell) ((JButton) e.getSource()).getClientProperty("cell");
        chainReveal(cell);
    }
}
