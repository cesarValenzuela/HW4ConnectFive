package cs3331;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        //gui.addMenuNGListener(new MenuListener());

        gui.addPaintHelperListener(new PaintHelperListener());
        gui.addPaintHelper2Listener(new PaintHelper2Listener());
    }

    class PlayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"15x15", "9x9"};
            Object[] yesOrNo = {"Yes" , "No"};
            int n = JOptionPane.showOptionDialog(gui,
                    "pick a size", "New Game",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            // 15 x 15
            if (n == JOptionPane.YES_OPTION) {
                int confirm = JOptionPane.showOptionDialog(gui, "Start NEW GAME?", "confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, yesOrNo, yesOrNo[1]);
                if(confirm == JOptionPane.YES_OPTION) {
                    gui.dispose();
                    new Controller(new Board(15),new ConnectFive(15));
                }
                // 9 x 9
            } else if (n == NO_OPTION) {
                int confirm = JOptionPane.showOptionDialog(gui, "Start NEW GAME?", "confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, yesOrNo, yesOrNo[1]);
                if(confirm == JOptionPane.YES_OPTION) {
                    gui.dispose();
                    new Controller(new Board(9), new ConnectFive(9));
                }
            } else {
            }
        }
    }

    class PaintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.colorChooser();
            //gui.repaint();
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
            Sound.playTileSound();
            gui.passCoordinates(x, y);
            gui.repaint();
        }
    }

    class PaintHelperListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.colorChooserHelper('1');
            //gui.getPopup().repaint();
        }
    }

    class PaintHelper2Listener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            gui.colorChooserHelper('2');
            //gui.getPopup().repaint();
        }
    }


//    class MenuListener implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            //new game
//            gui.getMenuItem().setMnemonic(KeyEvent.VK_N);
//            gui.getMenuItem().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
//                    ActionEvent.ALT_MASK));
//
//        }
//    }

    public static void main(String[] args) {
        Board board = new Board(15);
        ConnectFive gui = new ConnectFive();
        new Controller(board, gui);
    }
}
