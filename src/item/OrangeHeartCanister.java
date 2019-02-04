package item;

import item.HeartCanister;

public class OrangeHeartCanister extends HeartCanister{
    public OrangeHeartCanister(int num){
        super(num);
        setItemName("Orange Heart Canister");
        setHealthBoost(75);
        setID(28);
    }
}
