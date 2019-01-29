package game;

public class Tile {
    boolean cleared, enterable = true, enemy = false, explored, hidItem;
    String type;

    public Tile(){
        int c = idGenerator(3);
        if (c == 1){
            type = "Forest";
            cleared = false;
            enterable = true;
        } else if (c == 2){
            type = "Plain";
            cleared = false;
            enterable = true;
        } else if (c == 3){
            type = "Desert";
            cleared = false;
            enterable = true;
        }

        c = idGenerator(3);
        if (c == 2){
            enemy = true;
        } else if (c == 3){
            hidItem = true;
        }

        explored = false;
    }

    private static int idGenerator(int amount){
        double a;
        int b;

        a = Math.random() * (amount);
        b = (int) Math.round(a);

        return b;
    }

    public void setExplored(boolean e){
        explored = e;
    }

    public void setHidItem(boolean i){
        hidItem = i;
    }

    public void setClear(boolean c){
        cleared = c;
    }

    public void setEnemy(boolean e){
        enemy = e;
    }

    public void setType(String s){
        type = s;
    }

}

//tile types: town, river, lake, village, wasteland, dungeon, castle, dungeonEntrance, dense forest
//dense forest entrance, castle entrance
