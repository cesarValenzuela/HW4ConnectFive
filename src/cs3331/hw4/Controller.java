package cs3331.hw4;

import javax.swing.*;
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

    private void HumanVHuman(int x, int y) {
        try {
            // player 1 goes first
            if (gui.isTurn()) {
                gui.getMessage().setText("Player 2's turn");
                gui.getBoardPanel().getP1().setMove(x, y);
                gui.getBoardPanel().getBoard().addDisc(gui.getBoardPanel().getP1().currX - 1, gui.getBoardPanel().getP1().currY - 1, 1);
                gui.setTurn(false);
            } else {
                gui.getMessage().setText("Player 1's turn");
                gui.getBoardPanel().getP2().setMove(x, y);
                gui.getBoardPanel().getBoard().addDisc(gui.getBoardPanel().getP2().currX - 1, gui.getBoardPanel().getP2().currY - 1, 2);
                gui.setTurn(true);
            }
        } catch (InValidDiskPositionException ex) {
            gui.getMessage().setText("INVALID PLACEMENT");
            Sound.playInvalidTileSound();
        } catch (Exception ex1) {
            System.out.println("TIE");
        }
        // checking which player won
        winHelper();
    }

    private void HumanVsAI(int x, int y) {
        try {

            gui.getMessage().setText("Player 2's turn");
            System.out.println("HUMAN MOVE");
            gui.getBoardPanel().getP1().setMove(x, y);
            gui.getBoardPanel().getBoard().addDisc(gui.getBoardPanel().getP1().currX - 1, gui.getBoardPanel().getP1().currY - 1, 1);

            //AI
            System.out.println("AI MOVE");
            gui.getBoardPanel().getP2().setMove(x,y);
            gui.getBoardPanel().getBoard().addDisc(gui.getBoardPanel().getP2().currX, gui.getBoardPanel().getP2().currY,2);

        } catch (InValidDiskPositionException ex1) {
            gui.getMessage().setText("INVALID PLACEMENT");
            Sound.playInvalidTileSound();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("oops tie");
        }
        winHelper();
    }

    private void winHelper() {
        if (gui.getBoardPanel().getBoard().getBoardWon()) {
            if (gui.getBoardPanel().getBoard().getWinner() == 1) {
                gui.getMessage().setText("PLAYER 1 WINS");
                gui.getBoardPanel().setVisible(false);
            } else {
                gui.getMessage().setText("PLAYER 2 WINS");
                gui.getBoardPanel().setVisible(false);
            }
            Sound.playWinSound();
        }
    }


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
                System.out.println("ez");
                gui.getBoardPanel().setP2('e');
                //HumanVsAI();

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
                if (gui.getBoardPanel().getP2().isReal()){
                    HumanVHuman(x,y);
                } else {
                    HumanVsAI(x,y);
                }

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


        public static void main (String[]args){
            Board board = new Board(15);
            ConnectFive gui = new ConnectFive();
            new Controller(board, gui);
        }
}
