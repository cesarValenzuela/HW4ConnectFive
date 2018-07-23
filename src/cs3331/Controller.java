package cs3331;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.JOptionPane.NO_OPTION;

/**
 * Author: Cesar Valenzuela
 * Date: 7/22/2018
 * Course:
 * Assignment:
 * Instructor:
 * T.A:
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

    }

    class PlayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"15x15", "9x9"};
            int n = JOptionPane.showOptionDialog(gui,
                    "pick a size", "New Game",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            if (n == JOptionPane.YES_OPTION) {
                //JOptionPane.showConfirmDialog(this, "Start NEW GAME?");
                gui.dispose();
                new Controller(new Board(15),new ConnectFive(15));
                //new ConnectFive(15);
            } else if (n == NO_OPTION) {
                //JOptionPane.showConfirmDialog(this, "Start NEW GAME?");
                gui.dispose();
                new Controller(new Board(9), new ConnectFive(9));
            } else {

            }
        }
    }

    class PaintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.colorChooser();
            gui.repaint();
        }
    }

    class EasyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("EASY PEASY");
        }
    }

    class MediumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("MEDIUM");
        }
    }

    class ClickAdapter extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            int x = gui.locateXY(e.getX());
            int y = gui.locateXY(e.getY());
            Sound.playSound();
            gui.passCoordinates(x, y);
            gui.repaint();
        }
    }

    //class

    public static void main(String[] args) {
        Board board = new Board(15);
        ConnectFive gui = new ConnectFive();
        new Controller(board, gui);
    }
}
