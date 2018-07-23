package cs3331;

public class MedCompAI extends Computer {
    public MedCompAI(int currPlayer, char symbol) {
        super(currPlayer, symbol);
    }

    // need to make this medium difficulty
    public int[] getComputerCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = (int) (Math.random() * 15) + 1;
        coordinates[1] = (int) (Math.random() * 15) + 1;
        return coordinates;
    }

    public int minimax(Board board, int depth, boolean isMax) {
        int best = 0;
        if (isMax) {
            best = -1000;

            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.size(); j++) {
                    if (board.getTiles(i, j) == null) {
                        // make the move
                        try {
                            board.addDisc(i, j, 2);
                        } catch (InValidDiskPositionException e) {
                            e.printStackTrace();
                        }

                        //call minimax Recursively and choose the max values
                        best = Math.max(best, minimax(board, depth + 1, !isMax));

                        // undo the move
                        board.removeTile(i,j);
                    }
                }
            }
        } else {
            best = 1000;
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.size(); j++) {
                    if (board.getTiles(i, j) == null) { // if board [i][j] == empty
                        // make the move
                        try {
                            board.addDisc(i, j, 2);
                        } catch (InValidDiskPositionException e) {
                            e.printStackTrace();
                        }
                        //call minimax Recursively and choose the max values
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        // undo the move
                        board.removeTile(i,j);
                    }
                }
            }

        }

        return best;
    }

    public int[] makeBestMove(Board board) {
        int bestVal = -1000;
        int[] coordinates = new int[2];

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.isValidPosition(i,j)) {

                    // make the move
                    try {
                        board.addDisc(i, j, 2);
                    } catch (InValidDiskPositionException e) {
                        e.printStackTrace();
                    } if (board.getBoardWon()) {
                        int moveVal = minimax(board, 0, false);

                        // undo
                        board.removeTile(i,j);

                        // if val of current move is > best val then update
                        if (moveVal > bestVal) {
                            coordinates[0] = i;
                            coordinates[1] = j;
                        }
                    }
                }
            }
        }
        return coordinates;
    }
}


