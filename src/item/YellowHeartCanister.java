package item;

public class YellowHeartCanister extends HeartCanister{
    public YellowHeartCanister(int num){
        super(num);
        setItemName("Yellow Heart Canister");
        setHealthBoost(50);
        setID(27);
        setSellValue(100);
    }
}
