package cs3331;

import java.awt.*;

/**
 * @author Andrea Fernanda Torres
 */
public abstract class Player {
    private Color tileColor;
    private int playerName;//will be useful for the future
    private char symbol;// should be 1 or 2 will be useful for the future
    private boolean isReal;// will be used later on once AI is implemented

    public Player(int currPlayer, char symbol) {
        this.playerName = currPlayer;
        this.symbol = symbol;
        isReal = true;
    }

    public char getSymbol() {
        return symbol;
    }

    public Color getTileColor(){
        return tileColor;
    }
    public void setTileColor(Color tcolor){
        this.tileColor=tcolor;
    }

    public int getPlayerName() {
        return playerName;
    }
    //Eventually we will implement the AI stuff here or related to here that is why we have the irReal
}
