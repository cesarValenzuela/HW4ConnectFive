package cs3331.hw5;

import cs3331.hw4.Board;
import cs3331.hw4.ConnectFive;
import cs3331.hw4.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Cesar Valenzuela
 * Date: 7/26/2018
 * Course:
 * Assignment:
 * Instructor:
 * T.A:
 */
public class ControllerFive extends Controller {

    private Board model;
    protected ConnectFiveGUI gui;

    public ControllerFive(Board model, ConnectFiveGUI gui) {
        super(model, gui);

        //gui.addNetworkListener(new NetworkListener());
    }

    class NetworkListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            gui.getNetworkButton().setVisible(false);
//            gui.setNetworkButton(new JButton(gui.getNETWORK_ON()));
            System.out.println("HELLO");
        }
    }

    public static void main(String[] args) {
        Board board = new Board(15);
        ConnectFiveGUI gui = new ConnectFiveGUI();
        new ControllerFive(board, gui);
    }
}
