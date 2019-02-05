package item;

import players.Player;

abstract public class Item {
	protected String description;
	private String itemName;
	private boolean stackable;
	private int stack, value, ID, sellValue;

	public Item(int num){
		stack = num;
	}

	public static Item[] itemList(){
		return new Item[]{new ItemEmpty(1), new FrappePotion(1), new GrandePotion(1), new MongoPotion(1), new FrappeManaPotion(1), new GrandeManaPotion(1), new MongoManaPotion(1), new GarlicBread(1)};
	}

	public static Item[] itemListMaster(String s){
		return new Item[]{new ItemEmpty(1), new FrappePotion(1), new GrandePotion(1), new MongoPotion(1),
				new FrappeManaPotion(1), new GrandeManaPotion(1), new MongoManaPotion(1), new GarlicBread(1),
				new UltimateCheesyGarlicBread(1), new Wooden(1, s), new Travelers(1, s),
				new Standard(1, s), new Soldiers(1, s), new Warriors(1, s), new Guardians(1, s),
				new Heros(1, s), new Legends(1, s), new OldDirtyClothing(1), new LeatherChaps(1),
				new Chainmail(1), new IronPlatemail(1), new SilverPlatemail(1), new TitaniumPlatemail(1),
				new SteelPlatemail(1), new DragonScalePlatemail(1), new EmptyHeartCanister(1), new RedHeartCanister(1), new YellowHeartCanister(1),
				new OrangeHeartCanister(1), new GreenHeartCanister(1), new BlueHeartCanister(1), new PurpleHeartCanister(1),
				new WhiteHeartCanister(1), new CrystalHeartCanister(1)};
	}

	public abstract int use(Player PC);

	public String getItemName() {
		return itemName;
	}

	protected void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isStackable() {
		return stackable;
	}

	protected void setStackable(boolean stackable) {
		this.stackable = stackable;
	}

	public int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}

	public int getSellValue(){
		return sellValue;
	}

	protected void setSellValue(int s){
		this.sellValue = s;
	}

	public int getStack() {
		return stack;
	}

	public void setStack(int stack) {
		this.stack = stack;
	}

	public Item(String name, String description) {
		this.itemName = name;
		this.description = description;
	}
	public Item(Item i) {
		this.itemName = i.itemName;
		this.description = i.description;
	}

	public int getID(){
		return ID;
	}

	public void setID(int i){
		this.ID = i;
	}

	public String examine() {
		return this.description;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Item)) {
			return false;
		}
		Item other = (Item) o;
		return (this.itemName.equals(other.itemName) && this.description.equals(other.description));
	}

	@Override
	public int hashCode() {
		int result = 37;
		result = 37 * result + itemName.hashCode();
		result = 37 * result + description.hashCode();
		return result;
	}
}