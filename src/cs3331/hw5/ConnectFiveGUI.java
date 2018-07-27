package cs3331.hw5;

import cs3331.hw4.ConnectFive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.SocketHandler;

/**
 * Author: Cesar Valenzuela
 * Date: 7/26/2018
 * Course:
 * Assignment:
 * Instructor:
 * T.A:
 */
public class ConnectFiveGUI extends ConnectFive {
    private  ImageIcon NETWORK_OFF;

    private ImageIcon NETWORK_ON = createImageIcon("wifi-connected.png");

    private JButton networkButton;

    private NetworkAdapter network;

    public ConnectFiveGUI() {
        //super();
        toolBar();
    }

    protected JToolBar toolBar(){
        JToolBar toolBar = super.toolBar();
        NETWORK_OFF = createImageIcon("wifi-disconnected.png");
        //NETWORK_ON = createImageIcon("wifi-connected.png");
        networkButton = new JButton(NETWORK_OFF);
        //networkButton.addActionListener(this::networkButtonClicked);
        networkButton.setToolTipText("Pair");
        networkButton.setFocusPainted(false);
        toolBar.add(networkButton);
        return toolBar;
    }

    void addNetworkListener(ActionListener nListener) {
        networkButton.addActionListener(nListener);
    }

    public JButton getNetworkButton() {
        return networkButton;
    }

    public void setNetworkButton(JButton networkButton) {
        this.networkButton = networkButton;
    }

    private void networkButtonClicked(ActionEvent actionEvent) {
        new Thread(() -> {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("127.0.0.1", 8000), 5000);
                //pairAsClient(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public ImageIcon getNETWORK_OFF() {
        return NETWORK_OFF;
    }

    public ImageIcon getNETWORK_ON() {
        return NETWORK_ON;
    }

    //        network = new NetworkAdapter(socket);
    //        network.setMessageListener(this);
    //        network.writeJoin();
    //        network.receiveMessages();
    //    }
//    private void pairAsClient(Socket socket) {
}
