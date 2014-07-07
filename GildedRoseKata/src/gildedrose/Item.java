package gildedrose;

public class Item {

	public String name;
	public int quality;
	public int sellIn;
	
	public Item (String name, int quality, int sellIn) {
		this.name = name;
		this.quality = quality;
		this.sellIn = sellIn;
	}

	public int getSellIn() {
		return sellIn;
	}

	public void setSellIn(int sellIn) {
		this.sellIn = sellIn;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
