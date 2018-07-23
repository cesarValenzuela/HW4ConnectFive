package cs3331;


import com.sun.webkit.ColorChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.ImageObserver;
import java.net.URL;
import java.text.AttributedCharacterIterator;

import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;


/**
 * Frame class for the graphical user interface of connect five.
 * Controller for handling events that affect the model and the view.
 *
 * @author Edgar Padilla
 */
public class ConnectFive extends JFrame {

    /**
     * directory where images are stored
     */
    private final static String IMAGE_DIR = "/image/";

    private JColorChooser tcc;

    private JLabel message;
    private BoardPanel boardPanel;
    private int squareSize = 15;
    private Color color;

    private JButton playButton;
    private JButton paintButton;
    private JButton easyButton;
    private JButton mediumButton;

    private JMenuItem menuItem;

    // player1 is true, player2 is false
    private boolean turn = true;

    /**
     * Constructor that initializes and adds all the components of the frame
     * including anonymous classes for the handlers.
     */
    public ConnectFive() {
        setTitle("Connect Five");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        createGUI(15);

        setVisible(true);
        pack();
    }

    public ConnectFive(int size) {
        super();
        createGUI(size);

        squareSize = size;
        setVisible(true);
        pack();
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * creates the buttons, and adds the other panels to create
     * the overall GUI of the connect 5 game.
     *
     * @param size the size of the board to be played
     */
    public void createGUI(int size) {
        //adding buttons (top)
        JPanel boardSizePanel = new JPanel(new FlowLayout());


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * creates the buttons, and adds the other panels to create
     * the overall GUI of the connect 5 game.
     *
     * @param size the size of the board to be played
     */
    public void createGUI(int size) {
        JPanel boardSizePanel = new JPanel(new FlowLayout());

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(boardSizePanel, BorderLayout.NORTH);
        jPanel.add(boardPan(size), BorderLayout.CENTER);
        jPanel.add(statusPanel(), BorderLayout.SOUTH);

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(menuBar(), BorderLayout.NORTH);
        jPanel1.add(toolBar(), BorderLayout.SOUTH);
        getContentPane().add(jPanel, BorderLayout.CENTER);
        getContentPane().add(jPanel1, BorderLayout.NORTH);



        // Handler for user input when placing a disc on the grid.
        boardPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(e.getX()>25 && e.getY()>25 && e.getX()<675 && e.getY()<675) { // blocks user of clicking outside box
                    int x = locateXY(e.getX());
                    int y = locateXY(e.getY());
                    message.setText("X: " + x + " " + "Y: " + y);
                    //   Sound.playSound();
                    passCoordinates(x, y);
                    repaint();
                }

            }//end mouse pressed
        });
    }

    private JMenuBar menuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");

        //when menu is open - pressing G triggers something
        menu.setMnemonic(KeyEvent.VK_G);
        menu.getAccessibleContext().setAccessibleDescription("Game Menu");

        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("New Game");
        menuItem.setIcon(createImageIcon("play.png"));


        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Play a new game");
        //menuItem.addActionListener();
        menu.add(menuItem);

        return menuBar;
    }

    private JToolBar toolBar() {
        JToolBar toolBar = new JToolBar("Connect5");

        playButton = new JButton(createImageIcon("play30.png"));
        paintButton = new JButton(createImageIcon("paint30.png"));
        easyButton = new JButton(createImageIcon("easy30.png"));
        mediumButton = new JButton(createImageIcon("medium30.png"));

    private ImageIcon createImageIcon(String filename) {
        URL imageURL = getClass().getResource(IMAGE_DIR + filename);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        }
        return null;
    }

        JButton playButton = new JButton(createImageIcon("play30.png"));
        playButton.addActionListener(e -> {
            Object[] options = {"15x15", "9x9"};
            Object[] yesOrNo = {"Yes" , "No"};
            int n = JOptionPane.showOptionDialog(this,
                    "pick a size", "New Game",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            // 15 x 15
            if (n == JOptionPane.YES_OPTION) {
                int comfirm = JOptionPane.showOptionDialog(this, "Start NEW GAME?", "comfirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, yesOrNo, yesOrNo[1]);
                if(comfirm == JOptionPane.YES_OPTION) {
                    this.dispose();
                    new ConnectFive(15);
                }
            // 9 x 9
            } else if (n == NO_OPTION) {
                int comfirm = JOptionPane.showOptionDialog(this, "Start NEW GAME?", "comfirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, yesOrNo, yesOrNo[1]);
                if(comfirm == JOptionPane.YES_OPTION) {
                    this.dispose();
                    new ConnectFive(9);
                }
            } else {



        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(menuBar(), BorderLayout.NORTH);
        jPanel1.add(toolBar(), BorderLayout.SOUTH);
        getContentPane().add(jPanel, BorderLayout.CENTER);
        getContentPane().add(jPanel1, BorderLayout.NORTH);

            }
        });

        JButton paintButton = new JButton(createImageIcon("paint30.png"));

        JButton easyButton = new JButton(createImageIcon("easy30.png"));
        JButton mediumButton = new JButton(createImageIcon("medium30.png"));

        playButton.setToolTipText("Play a new game.");
        playButton.setFocusPainted(false);
        playButton.setToolTipText("Play a new game.");
        playButton.setFocusPainted(false);

        paintButton.setToolTipText("Customize Disc Colors");

        easyButton.setToolTipText("Play against Easy Computer");
        mediumButton.setToolTipText("Play against Medium Computer");


        paintButton.setToolTipText("Customize Disc Colors");


        easyButton.setToolTipText("Play against Easy Computer");
        mediumButton.setToolTipText("Play against Medium Computer");


        toolBar.add(playButton);
        toolBar.add(paintButton);
        toolBar.add(easyButton);
        toolBar.add(mediumButton);
        return toolBar;

        toolBar.add(playButton);
        toolBar.add(paintButton);
        toolBar.add(easyButton);
        toolBar.add(mediumButton);
        return toolBar;
    }

    void addEasyListener(ActionListener eal) {
        easyButton.addActionListener(eal);
    }


    void addMediumListener(ActionListener mal) {
        mediumButton.addActionListener(mal);
    }
    void addPlayListener(ActionListener pal){
        playButton.addActionListener(pal);
    }

    void addPaintListener(ActionListener paintal) {
        paintButton.addActionListener(paintal);
    }
    /**
     * adds disc to the board and checks which player placed it
     *
     * @param x  the x-coordinate from pixels
     * @param y  the y-coordinate from pixels
     */
    private void passCoordinates(int x, int y) {
            try {

                if (turn) {
                    message.setText("Player 2's turn");
                    boardPanel.getBoard().addDisc(x - 1, y - 1, 1);
                    turn = false;
                } else {
                    message.setText("Player 1's turn");
                    boardPanel.getBoard().addDisc(x - 1, y - 1, 2);
                    turn = true;
                }
            } catch (PlayerWonException ex1) {

                if (turn) {

                    message.setText("PLAYER 1 IS THE WINNER!");
                    boardPanel.setVisible(false);
                } else {
                    message.setText("PLAYER 2 IS THE WINNER");
                    boardPanel.setVisible(false);
                }
                //winning sound here
                Sound.playWinSound();
            } catch (InValidDiskPositionException ex1) {
                message.setText("INVALID PLACEMENT: ALREADY OCCUPIED");
                Sound.playInvalidTileSound();

            } catch (Exception ex1) {
                System.out.println("Something else went wrong");
            }
        Sound.playTileSound();
    }

    public void addMouseListener(MouseListener e){
        boardPanel.addMouseListener(e);

    }

    void addMenuNGListener(ActionListener mgal) {
        menuItem.addActionListener(mgal);
    }
    /**
     * creates the board panel that displays the current game
     *
     * @param size
     * @return a panel that can be added to the window
     */
    private BoardPanel boardPan(int size) {

        boardPanel = new BoardPanel(new Board(size));
        boardPanel.setPreferredSize(new Dimension(725, 725));
        return boardPanel;
    }

    /**
     * Creates a panel that displays a text status about the game
     *
     * @return JPanel to be added to a window
     */
    private JPanel statusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(Color.DARK_GRAY);
        statusPanel.setPreferredSize(new Dimension(650, 50));
        message = new JLabel("Welcome to Connect Five");
        message.setForeground(Color.WHITE);
        message.setFont(new Font(message.getName(), Font.BOLD, 26));
        statusPanel.add(message);

        return statusPanel;
    }
    private JFrame frametmp;
    private JPanel popup;
    private void colorChooser(){

        //JDialog popUp=new JDialog(new JFrame(),"Testing","this is an example");
        popup=new JPanel();
        //popup.setLayout(new BoxLayout(popup,BoxLayout.Y_AXIS));
        frametmp=new JFrame("Color chooser");
        JLabel text= new JLabel("Please Select Which Player to Chane tile");

        JButton p1=new JButton("Player 1");
        p1.setBackground(boardPanel.getColorP1());
        JButton p2=new JButton("Player 2");
        p2.setBackground(boardPanel.getColorP2());
        p1.addActionListener(new Listener1());
        p2.addActionListener(new Listener2());
        popup.add(text);
        popup.add(p1);
        popup.add(p2);
        frametmp.add(popup);
        frametmp.setSize(250,150);
        frametmp.setVisible(true);
        frametmp.setResizable(false);
        frametmp.repaint();

    }
    private class Listener1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            colorChooserHelper('1');
           popup.repaint();
        }
    }
    private class Listener2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            colorChooserHelper('2');
            popup.repaint();
        }
    }



    private void colorChooserHelper(char player) {

        color = JColorChooser.showDialog(this, "pick", Color.ORANGE);
        if (color == null) {
            color = Color.RED;
        } else{
            if(player=='1')
                boardPanel.setColorP1(color);
            if(player=='2')
                boardPanel.setColorP2(color);
        }
        repaint();
    }

    /**
     * adds disc to the board and checks which player placed it
     *
     * @param x the x-coordinate from pixels
     * @param y the y-coordinate from pixels
     */
    private void passCoordinates(int x, int y) {
        try {

            if (turn) {
                message.setText("Player 2's turn");
                boardPanel.getBoard().addDisc(x - 1, y - 1, 1);
                turn = false;
            } else {
                message.setText("Player 1's turn");
                boardPanel.getBoard().addDisc(x - 1, y - 1, 2);
                turn = true;
            }
        /*} catch (Exception ex1) {
            if (turn) {
                boardPanel.setVisible(false);
                message.setText("PLAYER 1 IS THE WINNER!");
            } else {
                message.setText("PLAYER 2 IS THE WINNER");
                boardPanel.setVisible(false);
            }*/
        } catch (InValidDiskPositionException ex1) {
            message.setText("INVALID PLACEMENT: ALREADY OCCUPIED");

        } catch (Exception ex1) {
            System.out.println("ITS A TIE");

        }
    }

    /**
     * takes the pixels in the window and divides it by board size
     * to return the coordinate of each square in the board grid
     *
     * @param x
     * @return int coordinate of the square that was clicked on the board
     */
    private int locateXY(int x) {
        int pxlsize = 675;
        int gridSize = squareSize;
        int distance = pxlsize / gridSize;
        if (x > 25) {
            x = x - 25; // since we start at 25 we remove 25 in the calculations
        }
        int result = (int) Math.round(x / distance);
        return result + 1;
    }

    private ImageIcon createImageIcon(String filename) {
        URL imageURL = getClass().getResource(IMAGE_DIR + filename);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        }
        return null;
    }


    public void colorChooser() {

        color = JColorChooser.showDialog(this, "pick", Color.ORANGE);
        if (color == null) {
            color = Color.RED;
        } else{
            boardPanel.setColorP1(color);
        }
    }

    /**
     * adds disc to the board and checks which player placed it
     *
     * @param x the x-coordinate from pixels
     * @param y the y-coordinate from pixels
     */

    private void passCoordinates(int x, int y) {
        try {

            if (turn) {
                message.setText("Player 2's turn");
                boardPanel.getBoard().addDisc(x - 1, y - 1, 1);
                turn = false;
            } else {
                message.setText("Player 1's turn");
                boardPanel.getBoard().addDisc(x - 1, y - 1, 2);
                turn = true;
            }
        } catch (PlayerWonException ex1) {
            if (turn) {
                boardPanel.setVisible(false);
                message.setText("PLAYER 1 IS THE WINNER!");
            } else {
                message.setText("PLAYER 2 IS THE WINNER");
                boardPanel.setVisible(false);
            }
        } catch (InValidDiskPositionException ex1) {
            message.setText("INVALID PLACEMENT: ALREADY OCCUPIED");

        } catch (Exception ex1) {
            System.out.println("ITS A TIE");

        }
    }
    
    /**
     * takes the pixels in the window and divides it by board size
     * to return the coordinate of each square in the board grid
     *
     * @param x
     * @return int coordinate of the square that was clicked on the board
     */
    int locateXY(int x) {
        int pxlsize = 675;
        int gridSize = squareSize;
        int distance = pxlsize / gridSize;
        if (x > 25) {
            x = x - 25; // since we start at 25 we remove 25 in the calculations
        }
        int result = (int) Math.round(x / distance);
        return result + 1;
    }

    private ImageIcon createImageIcon(String filename) {
        URL imageURL = getClass().getResource(IMAGE_DIR + filename);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        }
        return null;
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }


    /**
     * Initializes the frame for the GUI and starts the application.
     */
    public static void main(String[] args) {
        new ConnectFive();
    }

}