import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Board implements ActionListener {

    private int height, width;

    private Cell[][] minefield;

    private JFrame frame;


    Board(int height, int width) {
        this.height = height;
        this.width = width;

        minefield = new Cell[height][width];

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Cell cell = new Cell();
                int[] position = {h, w};
                cell.position(position);
                if (new Random().nextInt(25)==0) {
                    cell.addMine();
                }
                minefield[h][w] = cell;
            }
        }

        frame = new JFrame("minesweeper");

        frame.pack();
        frame.setVisible(true);
    }

    void render() {
        //JFrame frame = new JFrame("minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(height, width));
        for (Cell[] row : minefield) {
            for (Cell cell : row) {
                JButton button = cell.render();
                button.addActionListener(this);
                button.putClientProperty("cell", cell);
                frame.add(button);
            }
        }

        frame.pack();
    }

    private int findBombs(int row, int col){
        int bombs = 0;
        if (isValid((row-1), (col-1))) {
            if (minefield[row-1][col-1].getMine()) {
                bombs++;
            }
        }
        if (isValid((row-1), (col))) {
            if (minefield[row-1][col].getMine()) {
                bombs++;
            }
        }
        if (isValid((row-1), (col+1))) {
            if (minefield[row-1][col+1].getMine()) {
                bombs++;
            }
        }
        if (isValid((row), (col-1))) {
            if (minefield[row][col-1].getMine()) {
                bombs++;
            }
        }
        if (isValid((row+1), (col-1))) {
            if (minefield[row+1][col-1].getMine()) {
                bombs++;
            }
        }
        if (isValid((row+1), (col))) {
            if (minefield[row+1][col].getMine()) {
                bombs++;
            }
        }
        if (isValid((row+1), (col+1))) {
            if (minefield[row+1][col+1].getMine()) {
                bombs++;
            }
        }
        if (isValid((row), (col+1))) {
            if (minefield[row][col+1].getMine()) {
                bombs++;
            }
        }

        return bombs;
    };

    private boolean isValid(int row, int col){
        return (row >= 0) && (row < height) &&
                (col >= 0) && (col < width);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource();
        Cell cell = (Cell) button.getClientProperty("cell");
        cell.reveal();
        int[] position = cell.getPosition();
        cell.findBombs(findBombs(position[0], position[1]));
        render();
        //button.setBackground(Color.green);
    }
}
