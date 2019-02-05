package item;

public class GreenHeartCanister extends HeartCanister{
    public GreenHeartCanister(int num){
        super(num);
        setItemName("Green Heart Canister");
        setHealthBoost(100);
        setID(29);
        setSellValue(200);
    }
}
