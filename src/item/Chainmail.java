package item;

public class Chainmail extends Armor{
    public Chainmail(int num){
        super(num);
        setItemName("Chainmail Armor");
        setProtection(10);
        setID(19);
        setSellValue(150);
    }
}
