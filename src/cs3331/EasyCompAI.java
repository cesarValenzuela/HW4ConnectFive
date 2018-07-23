package cs3331;import java.util.Random;

public class EasyCompAI extends Computer {
    public EasyCompAI(int currPlayer, char symbol) {
        super(currPlayer, symbol);
    }

    @Override
    public void setMove(int x, int y) {

    }

    @Override
    public void setMove() {
        int[] coordinates= getComputerCoordinates();
        currY=coordinates[0];
        currX=coordinates[1];
    }

    @Override
    public int[] getMove() {
        return new int[]{currY,currX};
    }

    public int[] getComputerCoordinates() {
        int[] coordinates=new int[2];
        coordinates[0]=(int)(Math.random()*15)+1;
        coordinates[1]=(int)(Math.random()*15)+1;
        return coordinates;
    }


}
