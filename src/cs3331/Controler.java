/*package cs3331;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectFive {
    //private ConnectFiveGUI mGUI;
    private Board mBoard;
  /*  private ConnectFive(Board board, ConnectFiveGUI gui){
        mGUI=gui;
        mBoard=board;
        run();
    }*/
  /*
    private void run(){
        Player p1=new Human(1,'1');
        Player p2;

        if(/*2 player, probably call a method from the GUI or something else equally as cool*/ /*) {
  /*         p2 = new Human(2,'2');
        }else {
           p2 = new Computer(2,'2');
        }
        boolean p1Turn=true;
        boolean gameOver=false;
        while(!gameOver){
            if(p1Turn){
                p1.makeMove();
                p1Turn=false;
                gameOver=mBoard.getBoardWon();
            }else{
                p2.makeMove();
                p1Turn=true;
                gameOver=mBoard.getBoardWon();
            }
        }

    }
    */
  /*
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
        } catch (/*mboard.getBoardWon()*/ //) {

 /*           if (turn) {

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

    public static void main(String[] args){
        new Controler(new Board(15),new ConnectFiveGUI());
    }
}
*/