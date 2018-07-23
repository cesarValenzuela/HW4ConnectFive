package cs3331;

public class Human extends Player {

    final boolean isReal = true;

    public Human(int currPlayer, char symbol) {
        super(currPlayer, symbol);
    }

    @Override
    public void setMove(int x, int y) {
        super.currX=x;
        super.currY=y;
    }

    @Override
    public int[] getMove() {
        return new int[]{this.currY,this.currX};
    }
}
