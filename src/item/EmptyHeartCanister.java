package item;

public class EmptyHeartCanister extends HeartCanister{
    public EmptyHeartCanister(int num){
        super(num);
        setItemName("None");
        setHealthBoost(0);
    }
}
