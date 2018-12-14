package shop;

import players.Player;
import item.Item;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Shop {
    private ArrayList<Item> shopList = new ArrayList<>();
    private String shopName;

    protected void setShopList(ArrayList<Item> shopList){
        this.shopList = shopList;
    }

    protected void setShopName(String shopName){
        this.shopName = shopName;
    }

    public void shop(Player user){
        String choiceString;
        int choice = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the " + shopName + ", " + user.getName());
        System.out.println("What would you like to buy? " + "You have: " + user.getGold());
        byte num = 1;
        for(Item i : shopList) {
            System.out.println(num + ". " + i.getItemName() + " (" + i.getValue() + " Gold)");
            num++;
        }
        System.out.println("-1. Leave the " + shopName);
        shopChoice(user, input.nextLine());
    }

    public void shopChoice(Player user, String choice){
        byte choiceNum;
        /*if(!game.isNumber(choice)){
            System.out.println("Pick a valid choice.");
            shop(user, game);
        }*/
        choiceNum = Byte.parseByte(choice);
        if (choiceNum == -1){
            //game.displayOptionsMenu();
        } else if (user.getGold() >= shopList.get(choiceNum - 1).getValue()){
            user.spendGold(shopList.get(choiceNum - 1).getValue());
            System.out.println("You got a " + shopList.get(choiceNum - 1).getItemName() + "!");
            user.getInventory().addNewItem(shopList.get(choiceNum - 1).getID(), user.getWeaponType());
            shop(user);
        } else {
            System.out.println("Not enough Gold for that.");
            shop(user);
        }
    }

}