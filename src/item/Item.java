package item;

abstract public class Item {
	/**
	 * This class is not currently doing anything as of this point, will be implemented
	 * later. Once finished, this class will handle all items and their functions. 
	 */
	protected String name;
	protected String description;


	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public Item(Item i) {
		this.name = i.name;
		this.description = i.description;
	}
	public String getName() {
		return this.name;
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
		return (this.name.equals(other.name) && this.description.equals(other.description));
	}

	@Override
	public int hashCode() {
		int result = 37;
		result = 37 * result + name.hashCode();
		result = 37 * result + description.hashCode();
		return result;
	}
}