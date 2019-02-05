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
        int choice;
        Scanner input = new Scanner(System.in);
        boolean buying, selling, shopping = true;

        System.out.println("Welcome to the " + shopName + ", " + user.getName());

        while(shopping){
            System.out.println("Would you like to buy or sell?\n1. Buy \n2. Sell \n-1. Leave");
            choiceString = input.nextLine();
            while(!choiceString.equals("1") && !choiceString.equals("2") && !choiceString.equals("-1")){
                System.out.println("Please enter a valid option.");
                choiceString = input.nextLine();
            }

            if(choiceString.equals("1")){
                buying = true;
                while (buying){
                    System.out.println("What would you like to buy? " + "You have: " + user.getGold());
                    byte num = 1;
                    for(Item i : shopList) {
                        System.out.println(num + ". " + i.getItemName() + " (" + i.getValue() + " Gold)");
                        num++;
                    }
                    choiceString = input.nextLine();
                    while(choiceString.equals("0") || Integer.parseInt(choiceString) < -1 || Integer.parseInt(choiceString) > user.getInventory().getItemList().size()){
                        System.out.println("Please enter a valid option.");
                        choiceString = input.nextLine();
                    }
                    System.out.println("-1. Quit buying");
                    shopChoice(user, choiceString);
                    if(choiceString.equals("-1")){
                        buying = false;
                    }
                }
            } else if (choiceString.equals("2")){
                selling = true;
                while(selling){
                    System.out.println("What would you like to sell?");
                    user.getInventory().listItems();
                    System.out.println("Enter -1 to go back");
                    choiceString = input.nextLine();
                    while(choiceString.equals("0") || Integer.parseInt(choiceString) < -1 || Integer.parseInt(choiceString) > user.getInventory().getItemList().size()){
                        System.out.println("Please enter a valid option.");
                        choiceString = input.nextLine();
                    }
                    try{
                        choice = Integer.parseInt(choiceString);
                        switch(choice){
                            case -1:
                                selling = false;
                                break;
                            case -2:
                                System.out.println("Enter the item number, not the item name!");
                                break;
                            default:
                                choice--;
                                try{
                                    user.gainGold(user.getInventory().getItemList().get(choice).getSellValue());
                                    System.out.print(" for selling 1 "
                                            + user.getInventory().getItemList().get(choice).getItemName() + "!\n");
                                    user.getInventory().getItemList().get(choice).setStack(user.getInventory().getItemList().get(choice).getStack() - 1);
                                } catch (IndexOutOfBoundsException blarg){
                                    System.out.println("No item in that slot");
                                    break;
                                }
                                user.getInventory().removeItems();
                        }
                    } catch (NumberFormatException rarg){
                        System.out.println("Invalid input entered, please try again.");
                    }
                }
            } else {
                shopping = false;
            }
        }
    }

    public void shopChoice(Player user, String choice){
        byte choiceNum;
        try{
            choiceNum = Byte.parseByte(choice);
            if (user.getGold() >= shopList.get(choiceNum - 1).getValue()){
                user.spendGold(shopList.get(choiceNum - 1).getValue());
                System.out.println("You got a " + shopList.get(choiceNum - 1).getItemName() + "!");
                user.getInventory().addNewItem(shopList.get(choiceNum - 1).getID(), user.getWeaponType());
            } else {
                System.out.println("Not enough Gold for that.");
            }
        } catch (NumberFormatException narf) {
            System.out.println("Invalid input entered, please try again.");
        }
    }

}