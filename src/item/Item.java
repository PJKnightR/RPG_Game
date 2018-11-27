package item;

import players.Player;

abstract public class Item {
	/**
	 * This class is not currently doing anything as of this point, will be implemented
	 * later. Once finished, this class will handle all items and their functions. 
	 */

	protected String description;
	private String itemName;
	private int value;
	private boolean stackable;
	private int stack;

	public Item(int num){
		stack = num;
	}

	public static Item[] itemList(){
		return new Item[]{new ItemEmpty(0)};
	}

	public abstract int use(Player PC);

	public String getItemName() {
		return itemName;
	}

	protected void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}

	public boolean isStackable() {
		return stackable;
	}

	protected void setStackable(boolean stackable) {
		this.stackable = stackable;
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

	// abstract public void use();

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