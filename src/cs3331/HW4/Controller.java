package cs3331.hw4;//lol

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


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

    private static Board model;
    private static ConnectFive gui;

    private Controller(Board model, ConnectFive gui) {
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

    private void HumanVsAI(int x, int y,Board board) {
        try {

            gui.getMessage().setText("Player 2's turn");
            System.out.println("HUMAN MOVE");
            gui.getBoardPanel().getP1().setMove(x, y);
            gui.getBoardPanel().getBoard().addDisc(gui.getBoardPanel().getP1().currX - 1, gui.getBoardPanel().getP1().currY - 1, 1);

            //AI
            System.out.println("AI MOVE");
            gui.getBoardPanel().getP2().setMove(x,y,board);
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
    static class PlayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sizerequest("Start new game?");
        }
    }

    /**
     * Action Listener for the Paintbrush Button
     */
    static class PaintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.colorChooser();
        }
    }

    /**
     * Action Listener for easy AI button
     */
    static class EasyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Controller.sizerequest2("Start a new game against easyAI?",'e');

        }
    }
    private static void easyListenerHelper(int boardSize){
        System.out.println("ez");
        Color p1ColorTmp=gui.getBoardPanel().getColorP1();
        gui.dispose();
        new Controller(new Board(boardSize), new ConnectFive(boardSize,'e'));
        gui.getBoardPanel().setP2('e');
        gui.getBoardPanel().setColorP1(p1ColorTmp);
        gui.getBoardPanel().getP1().setTileColor(p1ColorTmp);
        gui.getBoardPanel().setColorP2(Color.BLACK);

    }
    private static void MediumListenerHelper(int boardSize){
        System.out.println("MEDIUM");
        gui.getBoardPanel().setP2('m');
        gui.dispose();
        new Controller(new Board(boardSize), new ConnectFive(boardSize,'m'));
        gui.getBoardPanel().setColorP2(Color.BLACK);
        gui.getBoardPanel().setP2('m');
    }
    /**
     * Action Listener for medium AI button
     */
    static class MediumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Controller.sizerequest2("Start a new game against mediumAI?",'m');
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
            System.out.println(gui.getBoardPanel().getP2().getIsReal());
            //Everytime we switch from hardness or comp to 2p we MUST create a new Board
            System.out.println("P2 is of  type: "+gui.getBoardPanel().getP2().getClass());
            if (gui.getBoardPanel().getP2() instanceof Human) {
                HumanVHuman(x, y);
            }else if (gui.getBoardPanel().getP2() instanceof MedCompAI){
                HumanVsAI(x, y,model);//medium we need to be able to pass the board method
            }else if(gui.getBoardPanel().getP2() instanceof EasyCompAI){
                HumanVsAI(x,y,model);//easy

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

    private static void sizerequest2(String text, char test) {
        Object[] options = {"15x15", "9x9"};
        Object[] yesOrNo = {"Yes", "No"};
        Sound.playAlertSound();

        int confirm = JOptionPane.showOptionDialog(gui, text, "confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, yesOrNo, yesOrNo[1]);

        if (confirm == JOptionPane.YES_OPTION) {
            int n = JOptionPane.showOptionDialog(gui,
                    "pick a size", "New Game",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            // 15 x 15
            if (n == JOptionPane.YES_OPTION) {
                if (test == 'e') {
                    easyListenerHelper(15);
                } else if (test == 'm') {
                    MediumListenerHelper(15);
                }
            } else {
                if (test == 'e') {
                    easyListenerHelper(9);
                } else if (test == 'm') {
                    MediumListenerHelper(9);
                }
            }
        }
    }
    private static void sizerequest(String text){
        Object[] options = {"15x15", "9x9"};
        Object[] yesOrNo = {"Yes", "No"};
        Sound.playAlertSound();

        int confirm = JOptionPane.showOptionDialog(gui,text, "confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, yesOrNo, yesOrNo[1]);

        if (confirm == JOptionPane.YES_OPTION) {
            int n = JOptionPane.showOptionDialog(gui,
                    "pick a size", "New Game",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            // 15 x 15
            if (n == JOptionPane.YES_OPTION) {
                gui.dispose();
                new Controller(new Board(15), new ConnectFive(15,'j'));
            }else{
                gui.dispose();
                new Controller(new Board(9), new ConnectFive(9,'j'));
            }
        }
    }

    public static void main (String[]args){
        Board board = new Board(15);
        ConnectFive gui = new ConnectFive();
        new Controller(board, gui);
    }
}