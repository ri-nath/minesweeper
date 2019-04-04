import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class Board implements ActionListener {

    private int height, width;
    private boolean game;
    private Cell[][] minefield;
    private JFrame frame;
    private ImageIcon mineIcon, flagIcon;

    Board(int height, int width, int difficulty) {
        this.height = height;
        this.width = width;

        frame = new JFrame("minesweeper");
        minefield = new Cell[height][width];

        mineIcon = loadImageIcon("resources/mine.jpg", 20);
        flagIcon = loadImageIcon("resources/flag.jpg", 20);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = new Cell();
                if (new Random().nextInt(difficulty) == 0) cell.addMine();
                cell.position(row, col);
                cell.addIcon(flagIcon, mineIcon);
                minefield[row][col] = cell;
            }
        }

        for (Cell[] row : minefield) {
            for (Cell cell : row) {
                count(cell);

                JButton button = cell.render();
                button.addActionListener(this);
                button.putClientProperty("cell", cell);
                frame.add(button);
            }
        }

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new GridLayout(height, width));
        frame.pack();
        frame.setVisible(true);

        game = true;
    }

    private ImageIcon loadImageIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage();
        image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        return icon;
    }

    private void count(Cell target){
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

    private void flood(Cell origin){
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
            flood(cell);
        }
    };

    private boolean validate(int row, int col){
        return (row >= 0) && (row < height) &&
                (col >= 0) && (col < width);
    }

    private void end() {
        game = false;

        for (Cell[] row : minefield) {
            for (Cell cell : row) {
                cell.reveal();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if (game) {
            Cell cell = (Cell) ((JButton) event.getSource()).getClientProperty("cell");
            if ((event.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
                cell.flag();
            } else {
                if (cell.checkMine()) {
                    end();
                }
                flood(cell);
            }
        }
    }
}
