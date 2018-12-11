package item;

import players.Player;

abstract public class Item {
	protected String description;
	private String itemName;
	private boolean stackable;
	private int stack, value;

	public Item(int num){
		stack = num;
	}

	public static Item[] itemList(){
		return new Item[]{new ItemEmpty(1), new FrappePotion(1), new GrandePotion(1), new MongoPotion(1), new FrappeManaPotion(1), new GrandeManaPotion(1), new MongoManaPotion(1), new GarlicBread(1)};
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