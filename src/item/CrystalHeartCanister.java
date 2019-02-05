package item;

public class CrystalHeartCanister extends HeartCanister{
    public CrystalHeartCanister(int num){
        super(num);
        setItemName("Crystal Heart Canister");
        setHealthBoost(400);
        setID(33);
        setSellValue(350);
    }
}
