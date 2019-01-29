package game;

public class Board {
    int curPosX, curPosY, prevPosX, prevPosY, clearedTiles, boardNumber;
    Tile curBoard [][];

    public Board(int num){
        boardNumber = num + 1;
    }

    public void generateNewBoard(){
        curBoard = new Tile[9][9];
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                curBoard[r][c] = new Tile();
            }
        }
    }

    public void displayBoard(){
        System.out.print("\n");
        for(int c = 0; c < 9; c++){
            for(int r = 0; r < 9; r++){
                if(r == curPosX && c == curPosY){
                    System.out.print("H ");
                } else if(curBoard[r][c].cleared){
                    System.out.print("O ");
                } else if (!curBoard[r][c].enterable) {
                    System.out.print("- ");
                } else if (!curBoard[r][c].explored){
                    System.out.print("? ");
                } else if (curBoard[r][c].enemy){
                    System.out.print("X ");
                } else if (curBoard[r][c].hidItem){
                    System.out.print("I ");
                } else if (curBoard[r][c].explored){
                    System.out.print("E ");
                } else {
                    System.out.print("! ");
                }
            }
            System.out.print("\n");
        }
    }

    public void setExplored(){
        curBoard[curPosX][curPosY].setExplored(true);
    }

    public void setEnemy(){
        curBoard[curPosX][curPosY].setEnemy(false);
    }

    public void setClear(){
        curBoard[curPosX][curPosY].setClear(true);
    }

    public void setHidItem(){
        curBoard[curPosX][curPosY].setHidItem(false);
    }

    public void setCurPosX(int x){
        prevPosX = curPosX;
        curPosX = x;
    }

    public int getCurPosX(){
        return curPosX;
    }

    public void setCurPosY(int y){
        prevPosY = curPosY;
        curPosY = y;
    }

    public int getCurPosY(){
        return curPosY;
    }

    public Tile getTile(int x, int y){
        return curBoard[x][y];
    }

    public Tile getCurrentTile(){
        return curBoard[curPosX][curPosY];
    }
}
