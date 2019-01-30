package game;

public class Tile {
    boolean cleared, enterable = true, enemy = false, explored, hidItem, shop;
    String type;

    public Tile(){
        int c = idGenerator(6);
        if (c == 1){
            type = "Forest";
            cleared = false;
            enterable = true;
            shop = false;
        } else if (c == 2){
            type = "Plain";
            cleared = false;
            enterable = true;
            shop = false;
        } else if (c == 3){
            type = "Desert";
            cleared = false;
            enterable = true;
            shop = false;
        } else if (c == 4){
            type = "Woods";
            cleared = false;
            enterable = true;
            shop = false;
        } else if (c == 5){
            type = "Ruins";
            cleared = false;
            enterable = true;
            enemy = true;
            hidItem = true;
            shop = false;
        } else if (c == 6){
            type = "Dungeon";
            cleared = false;
            enterable = true;
            enemy = true;
            shop = false;
        } else if (c == 7){
            type = "Village";
            cleared = false;
            enterable = true;
            shop = true;
        }

        if (c != 5 && c != 7){
            c = idGenerator(3);
            if (c == 2){
                enemy = true;
            } else if (c == 3){
                hidItem = true;
            }
        }

        explored = false;
    }

    public Tile(int c, boolean e, boolean i){
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
        } else if (c == 4){
            type = "Woods";
            cleared = false;
            enterable = true;
        } else if (c == 5){
            type = "Ruins";
            cleared = false;
            enterable = true;
            enemy = true;
            hidItem = true;
        } else if (c == 6){
            type = "Dungeon";
            cleared = false;
            enterable = true;
        } else if (c == 7){
            type = "Village";
            cleared = false;
            enterable = true;
            shop = true;
        }

        enemy = e;
        hidItem = i;

        explored = false;
    }

    private static int idGenerator(int amount){
        double a;
        int b;

        a = 1 + Math.random() * (amount);
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

//tile types: town, river, lake, wasteland, castle, dungeonEntrance, dense forest
//dense forest entrance, castle entrance
