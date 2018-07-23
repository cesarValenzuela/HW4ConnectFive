package cs3331;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**
 * Class that acts as the Panel to display the model (board)
 * as a grid of 15 by 15 or 9 by 9.
 *
 * @author Edgar Padilla
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    /**
     * Board model.
     */
    private Board board;
    private int grid; // size of grid

    private Color colorP1 = Color.RED;
    private Color colorP2 = Color.BLACK;

    /**
     * Creates an instance of this panel for the discs board.
     */
    public BoardPanel(Board board) {
        super(true);
        this.board = board;
        grid = this.board.size();
        setOpaque(true);
        setBackground(Color.CYAN);
    }

    public int setSize(int grid) { // set size 9 or 15
        return this.grid = grid;
    }

    /**
     * Draws the discs board by calling the paint method.
     */
    public void drawBoard() {
        repaint();
    }

    public Color getColorP1() {
        return colorP1;
    }

    public void setColorP1(Color colorP1) {
        this.colorP1 = colorP1;
    }

    public Color getColorP2() {
        return colorP2;
    }

    public void setColorP2(Color colorP2) {
        this.colorP2 = colorP2;
    }

    public Board getBoard() {
        return board;
    }


    /**
     * Paints the state of the board along with the discs placed
     *
     * @param g
     */
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        int size = 675; // multiple of 9 and 15
        int n = size / grid;
        // for 15x15 distance in-between lines is 45px
        // for 9x9 its 75px
        for (int i = 0; i <= 15; i++) {
            //vertical lines
            // starts at 25px
            g.drawLine(25 + i * n, 25, 25 + i * n, 700);
            //horizontal
            g.drawLine(25, 25 + i * n, 700, 25 + i * n);
        }

        int pxlTotal = 675;
        int temp = pxlTotal / grid;
        int discPxl;
        if (grid == 9) {
            discPxl = 75;
        } else {
            discPxl = 45;
        }
        Graphics2D g22d = (Graphics2D) g;

        // Traverses array of Squares and if it finds a disc, it paints
        // a circle
        for (int i = 0; i < board.size(); i++) {

            for (int j = 0; j < board.size(); j++) {

                if (board.getTiles(i, j) != null && board.getTiles(i, j).getPlayer() == 1) {
                    Ellipse2D.Double circle = new Ellipse2D.Double(i * temp + 25, j * temp + 25, discPxl, discPxl);
                    g22d.setPaint(colorP1);
                    g22d.fill(circle);
                } else if (board.getTiles(i, j) != null && board.getTiles(i, j).getPlayer() == 2) {
                    Ellipse2D.Double circle = new Ellipse2D.Double(i * temp + 25, j * temp + 25, discPxl, discPxl);
                    g22d.setPaint(Color.BLACK);
                    g22d.fill(circle);
                }
            }
        }
    }

}