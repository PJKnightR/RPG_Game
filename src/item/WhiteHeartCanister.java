package item;

public class WhiteHeartCanister extends HeartCanister{
    public WhiteHeartCanister(int num){
        super(num);
        setItemName("White Heart Canister");
        setHealthBoost(350);
        setID(32);
        setSellValue(300);
    }
}
