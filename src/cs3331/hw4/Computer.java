package cs3331.hw4;

public abstract class Computer extends Player {
    final boolean isReal = false;
    public Computer(int currPlayer, char symbol) {
        super(currPlayer, symbol);
    super.isReal=false;
    }
    public abstract void setMove();
}
