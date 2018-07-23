package cs3331;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.NO_OPTION;

/**
 * Author: Cesar Valenzuela
 * Date: 7/22/2018
 * Course: CS3331
 * Assignment: HW4
 * Instructor: Edgar Padilla
 *
 * Controller for Connect Five game
 */
public class Controller {

    private Board model;
    private ConnectFive gui;

    public Controller(Board model, ConnectFive gui) {
        this.model = model;
        this.gui = gui;

        //add listeners to GUI
        gui.addPlayListener(new PlayListener());
        gui.addPaintListener(new PaintListener());
        gui.addEasyListener(new EasyListener());
        gui.addMediumListener(new MediumListener());
        gui.addMouseListener(new ClickAdapter());

        gui.addPaintHelperListener(new PaintHelperListener());
        gui.addPaintHelper2Listener(new PaintHelper2Listener());
    }

    //private void

    /**
     * Action Listener for the Play Button on the Tool Bar
     */
    class PlayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"15x15", "9x9"};
            Object[] yesOrNo = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(gui,
                    "pick a size", "New Game",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            // 15 x 15
            if (n == JOptionPane.YES_OPTION) {
                int confirm = JOptionPane.showOptionDialog(gui, "Start NEW GAME?", "confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, yesOrNo, yesOrNo[1]);
                if (confirm == JOptionPane.YES_OPTION) {
                    gui.dispose();
                    new Controller(new Board(15), new ConnectFive(15));
                }
                // 9 x 9
            } else if (n == NO_OPTION) {
                int confirm = JOptionPane.showOptionDialog(gui, "Start NEW GAME?", "confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, yesOrNo, yesOrNo[1]);
                if (confirm == JOptionPane.YES_OPTION) {
                    gui.dispose();
                    new Controller(new Board(9), new ConnectFive(9));
                }
            } else {
            }
        }
    }

    /**
     * Action Listener for the Paintbrush Button
     */
    class PaintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.colorChooser();
        }
    }

    /**
     * Action Listener for easy AI button
     */
    class EasyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("EASY PEASY");
        }
    }

    /**
     * Action Listener for medium AI button
     */
    class MediumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("MEDIUM");
        }
    }

    /**
     *
     */
    class ClickAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int x = gui.locateXY(e.getX());
            int y = gui.locateXY(e.getY());
            Sound.playTileSound();
            gui.passCoordinates(x, y);
            gui.repaint();
        }
    }

    /**
     *
     */
    class PaintHelperListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.colorChooserHelper('1');
        }
    }

    /**
     *
     */
    class PaintHelper2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.colorChooserHelper('2');
        }
    }


    public static void main(String[] args) {
        Board board = new Board(15);
        ConnectFive gui = new ConnectFive();
        new Controller(board, gui);
    }
}
