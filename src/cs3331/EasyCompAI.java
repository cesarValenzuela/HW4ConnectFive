package cs3331;

public class EasyCompAI extends CompPlayer {
    @Override
    public int[] getComputerCoordinates() {
        int[] coordinates=new int[2];
        coordinates[0]=(int)(Math.random()*15)+1;
        coordinates[1]=(int)(Math.random()*15)+1;
        return coordinates;
    }

}
