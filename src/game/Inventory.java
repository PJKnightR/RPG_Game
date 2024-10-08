package game;

import item.Item;
import players.Player;

import java.util.*;

public class Inventory {
    ArrayList<Item> itemList;
    //Map<String, Integer> itemMap;


    public Inventory() {
        //itemMap = new HashMap<>();
        itemList = new ArrayList<>();
    }

    public void addRandomItem(){
        if (itemList.size() < 60) {
            Item item = Item.itemList()[idGenerator()];
            System.out.println("You found a " + item.getItemName());
            //if(itemMap.containsKey(item.getItemName())){
                //itemMap.put(item.getItemName(),1 + itemMap.get(item.getItemName()));
            //} else {
                //itemMap.put(item.getItemName(), 1);
            //}
            itemList.add(item);
        } else {
            System.out.println("Your inventory is full");
        }
        stackItem();
    }

    public void addNewItem(Item i){
        itemList.add(i);
        stackItem();
    }

    public void addNewItem(int ID, String s){
        Item item = Item.itemListMaster(s)[ID];
        itemList.add(item);
        stackItem();
    }

    public void useItem(Player user, Battle bat){ //need methods during and not during battle
        Scanner scan = new Scanner(System.in);
        int position = 0;
        if (itemList.isEmpty()){
            System.out.println("No items in Inventory");
            position = -1;
            bat.playerMove();
        }
        while(position != -1){
            System.out.println("What item do you want to use?");
            listItems();
            System.out.println("Enter -1 to go back");
            position = scan.nextInt();
            switch(position){
                case -1:
                    bat.playerMove();
                    break;
                case -2:
                    System.out.println("Enter the item number, not the item name!");
                    break;
                default:
                    position--;
                    try{
                        itemList.get(position).use(user);
                    } catch (IndexOutOfBoundsException iob){
                        System.out.println("No item in that slot");
                        break;
                    }
                    removeItems();
                    position = -1;
                    //then the enemy will do stuff
            }
        }
    }

    public void useItemsOutside(Player user){
        Scanner scan = new Scanner(System.in);
        int position = 0, choice = 0;
        if (itemList.isEmpty()){
            System.out.println("No items in Inventory");
            position = -1;
            //call back to menu
        }
        while(position != -1){
            System.out.println("What item do you want to view?");
            listItems();
            System.out.println("Enter -1 to go back");
            position = scan.nextInt();
            switch(position){
                case -1:
                    //call back to menu
                    break;
                case -2:
                    System.out.println("Enter the item number, not the item name!");
                    break;
                default:
                    position--;
                    try{
                        itemList.get(position).getItemName();
                    } catch (IndexOutOfBoundsException iob){
                        System.out.println("No item in that slot");
                        choice = -1;
                        break;
                    }

                    if (choice != -1){
                        System.out.println("What would you like to do with " + itemList.get(position).getItemName() + " 1. Use" +
                                " 2. Examine 3. Toss Enter -1 to go back.");

                        while (choice != -1){
                            try {
                                choice = scan.nextInt();
                            } catch (InputMismatchException ime){
                                System.out.println("Please enter a valid number");
                                choice = -2;
                            }
                            if (choice != -2){
                                if (choice == 1){
                                    itemList.get(position).use(user);
                                    choice = -1;
                                } else if (choice == 2){
                                    System.out.println("Not currently implemented");
                                    choice = -1;
                                } else if (choice == 3) {
                                    System.out.print("You threw away a " + itemList.get(position).getItemName() + " ");
                                    itemList.get(position).setStack(itemList.get(position).getStack() - 1);
                                    choice = -1;
                                }
                            }

                        }
                    }


                    removeItems();
                    position = -1;
            }
        }

    }

    public void listItems(){
        if (itemList.isEmpty()){
            System.out.println("The Bag is empty");
        }
        int num = 1;
        for(Item i : itemList){
            System.out.println(num + ". " + i.getItemName() + " (x" + i.getStack() + ")");
            num++;
        }
    }

    private void stackItem(){
        Item[] stacking = itemList.toArray(new Item[itemList.size()]);
        byte num = Byte.valueOf(Integer.toString(stacking.length - 1)), num1 = 0;
        if(stacking.length > 1) {
            for (Item i : stacking) {
                for (byte j = num; j > num1; j--) {
                    //System.out.println(i.getItemName() + " | " + stacking[j].getItemName() + " | " + j);
                    if (i.isStackable() && stacking[j].isStackable() && stacking[j].getStack() > 0 && i.getItemName().equals(stacking[j].getItemName())) {
                        i.setStack(i.getStack() + 1);
                        stacking[j].setStack(0);
                    }
                }
                num1++;
            }
            itemList.clear();
            itemList = new ArrayList<>(Arrays.asList(stacking));
            removeItems();
        }

    }

    public void removeItems(){
        for(byte i = 0; i < itemList.size(); i++){
            if(itemList.get(i).getStack() == 0)
                itemList.remove(i);
        }
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    private int idGenerator(){
        double a;
        int b;

        a = 1 + Math.random() * (7 - 1);
        b = (int) Math.round(a);

        return b;
    }
}
