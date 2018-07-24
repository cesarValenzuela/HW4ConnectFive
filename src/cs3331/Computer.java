package cs3331;

public abstract class Computer extends Player {
    public Computer(int currPlayer, char symbol) {
        super(currPlayer, symbol);
        super.isReal=false;
    }
    public abstract void setMove();
}
