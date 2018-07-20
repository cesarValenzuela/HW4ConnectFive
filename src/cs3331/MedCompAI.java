package cs3331;

public class MedCompAI extends CompPlayer {
    @Override
    // need to make this medium difficulty
    public int[] getComputerCoordinates() {
        int[] coordinates=new int[2];
        coordinates[0]=(int)(Math.random()*15)+1;
        coordinates[1]=(int)(Math.random()*15)+1;
        return coordinates;
    }

}
