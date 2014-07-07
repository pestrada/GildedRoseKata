package gildedrose;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;


public class GildedRoseTests {
	
	private int initialQuality = 10;
	private int initialSellIn = 5;
	private Item item;

	// Normal Items
	
	@Test
	public void normal_items_update_decrements_sell_in_by_one () {
		getGildedRose().updateQuality(buildList(buildItem("NORMAL ITEM")));
		assertEquals(initialSellIn -1,  this.item.getSellIn());
	}
	
	@Test
	public void normal_item_before_sell_date_update_decreases_quality_by_one () {
		getGildedRose().updateQuality(buildList(buildItem("NORMAL ITEM")));
		assertEquals(initialQuality - 1, this.item.getQuality());
	}
	
	@Test
	public void normal_items_on_sell_date_update_decreases_quality_by_two () {
		initialSellIn = 0;
		getGildedRose().updateQuality(buildList(buildItem("NORMAL ITEM")));
		
		assertEquals(initialQuality - 2, item.getQuality());
	}
	
	@Test
	public void normal_item_after_sell_date_update_decreases_quality_by_two () {
		initialSellIn = -10;
		getGildedRose().updateQuality(buildList(buildItem("NORMAL ITEM")));
		assertEquals(initialQuality - 2, item.getQuality());
	}
	
	@Test
	public void normal_item_of_zero_quality_update_has_a_quality_of_zero () {
		initialQuality = 0;
		getGildedRose().updateQuality(buildList(buildItem("NORMAL ITEM")));
		assertEquals(0, this.item.getQuality());
	}
	
	// Aged Brie
	
	@Test
	public void aged_brie_update_decrements_sell_in_by_one () {
		getGildedRose().updateQuality(buildList(buildItem("Aged Brie")));
		assertEquals(initialSellIn -1,  this.item.getSellIn());
	}
	
	@Test
	public void aged_brie_before_sell_date_increments_quality_by_one () {
		getGildedRose().updateQuality(buildList(buildItem("Aged Brie")));
		assertEquals(initialQuality + 1,  this.item.getQuality());
	}
	
	@Test
	public void aged_brie_with_max_quality_update_should_not_change_the_quality () {
		initialQuality = 50;
		getGildedRose().updateQuality(buildList(buildItem("Aged Brie")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	@Test
	public void aged_brie_on_sell_date_update_should_increment_quality_by_2 () {
		initialSellIn = 0;
		getGildedRose().updateQuality(buildList(buildItem("Aged Brie")));
		assertEquals(initialQuality + 2,  this.item.getQuality());
	}

	@Test
	public void aged_brie_on_sell_date_near_max_quality_update_should_increment_quality_to_max_quality () {
		initialSellIn = 0;
		initialQuality = 49;
		getGildedRose().updateQuality(buildList(buildItem("Aged Brie")));
		assertEquals(initialQuality + 1,  this.item.getQuality());
	}
	
	@Test
	public void aged_brie_on_sell_date_at_max_quality_update_should_should_have_the_max_quality () {
		initialSellIn = 0;
		initialQuality = 50;
		getGildedRose().updateQuality(buildList(buildItem("Aged Brie")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	@Test
	public void aged_brie_after_sell_date_update_increments_quality_by_two () {
		initialSellIn = -10;
		getGildedRose().updateQuality(buildList(buildItem("Aged Brie")));
		assertEquals(initialQuality + 2,  this.item.getQuality());
	}
	
	@Test
	public void aged_brie_after_sell_date_at_max_quality_update_does_not_increment_quality () {
		initialSellIn = -10;
		initialQuality = 50;
		getGildedRose().updateQuality(buildList(buildItem("Aged Brie")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	// Sulfuras
	
	@Test
	public void sulfuras_update_does_not_change_the_sell_in () {
		getGildedRose().updateQuality(buildList(buildItem("Sulfuras, Hand of Ragnaros")));
		assertEquals(initialSellIn,  this.item.getSellIn());
	}
	
	@Test
	public void sulfuras_before_sell_date_update_does_not_change_quality () {
		getGildedRose().updateQuality(buildList(buildItem("Sulfuras, Hand of Ragnaros")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	@Test
	public void sulfuras_on_sell_date_update_does_not_change_quality () {
		initialSellIn = 0;
		getGildedRose().updateQuality(buildList(buildItem("Sulfuras, Hand of Ragnaros")));
		assertEquals(initialQuality,  this.item.getQuality());
	}

	@Test
	public void sulfuras_after_sell_date_update_does_not_change_quality () {
		initialSellIn = -10;
		getGildedRose().updateQuality(buildList(buildItem("Sulfuras, Hand of Ragnaros")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	// Backstage Pass
	
	@Test
	public void backstage_pass_update_decrements_sell_in_by_one () {
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialSellIn - 1,  this.item.getSellIn());
	}
	
	@Test
	public void backstage_pass_long_before_sell_date_update_increments_quality_by_one () {
		initialSellIn = 11;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality + 1,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_long_before_sell_date_at_max_quality_update_does_not_change_quality () {
		initialSellIn = 11;
		initialQuality = 50;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_medium_close_to_sell_date_upper_bound_increments_quality_by_two () {
		initialSellIn = 10;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality + 2,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_medium_at_max_quality_close_to_sell_date_upper_bound_does_not_change_quality () {
		initialSellIn = 10;
		initialQuality = 50;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality,  this.item.getQuality());
	}

	@Test
	public void backstage_pass_medium_close_to_sell_date_lower_bound_increments_quality_by_two () {
		initialSellIn = 6;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality + 2,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_medium_at_max_quality_close_to_sell_date_lower_bound_does_not_change_quality () {
		initialSellIn = 6;
		initialQuality = 50;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_very_close_to_sell_date_upper_bound_increments_quality_by_three () {
		initialSellIn = 5;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality + 3,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_at_max_quality_very_close_to_sell_date_upper_bound_does_not_change_quality () {
		initialSellIn = 5;
		initialQuality = 50;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality,  this.item.getQuality());
	}

	@Test
	public void backstage_pass_very_close_to_sell_date_lower_bound_increments_quality_by_three () {
		initialSellIn = 1;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality + 3,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_medium_at_max_quality_very_close_to_sell_date_lower_bound_does_not_change_quality () {
		initialSellIn = 1;
		initialQuality = 50;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_on_sell_date_update_has_zero_quality () {
		initialSellIn = 0;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(0,  this.item.getQuality());
	}
	
	@Test
	public void backstage_pass_after_sell_date_update_has_zero_quality () {
		initialSellIn = -10;
		getGildedRose().updateQuality(buildList(buildItem("Backstage passes to a TAFKAL80ETC concert")));
		assertEquals(0,  this.item.getQuality());
	}

	
	// Conjured Item
	
	@Test
	public void conjured_item_update_decrements_sell_in_by_one () {
		getGildedRose().updateQuality(buildList(buildItem("Conjured Mana Cake")));
		assertEquals(initialSellIn - 1,  this.item.getSellIn());
	}
	
	@Test
	public void conjured_item_before_sell_date_update_decrements_quality_by_two () {
		getGildedRose().updateQuality(buildList(buildItem("Conjured Mana Cake")));
		assertEquals(initialQuality - 2,  this.item.getQuality());
	}
	
	@Test
	public void conjured_item_at_zero_quality_before_sell_date__update_has_zero_quality () {
		initialQuality = 0;
		getGildedRose().updateQuality(buildList(buildItem("Conjured Mana Cake")));
		assertEquals(initialQuality,  this.item.getQuality());
	}

	@Test
	public void conjured_item_on_sell_date_update_decrements_quality_by_four () {
		initialSellIn = 0;
		getGildedRose().updateQuality(buildList(buildItem("Conjured Mana Cake")));
		assertEquals(initialQuality - 4,  this.item.getQuality());
	}
	
	@Test
	public void conjured_item_at_zero_quality_on_sell_date_update_has_zero_quality () {
		initialQuality = 0;
		initialSellIn = 0;
		getGildedRose().updateQuality(buildList(buildItem("Conjured Mana Cake")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	@Test
	public void conjured_item_after_sell_date_update_decrements_quality_by_four () {
		initialSellIn = -10;
		getGildedRose().updateQuality(buildList(buildItem("Conjured Mana Cake")));
		assertEquals(initialQuality - 4,  this.item.getQuality());
	}
	
	@Test
	public void conjured_item_at_zero_quality_after_sell_date_update_has_zero_quality () {
		initialQuality = 0;
		initialSellIn = -10;
		getGildedRose().updateQuality(buildList(buildItem("Conjured Mana Cake")));
		assertEquals(initialQuality,  this.item.getQuality());
	}
	
	@Test
	public void with_several_items () {
		Item item1 = new Item ("NORMAL ITEM", 5, 10);
		Item item2 = new Item ("Aged Brie", 3, 10);
		
		List<Item> items = new ArrayList<Item> ();
		items.add(item1);
		items.add(item2);
		
		getGildedRose().updateQuality(items);
		
		assertEquals(4, item1.getQuality());
		assertEquals(9, item1.getSellIn());
		
		assertEquals(4, item2.getQuality());
		assertEquals(9, item2.getSellIn());
	}
	
	// Helpers
	
	private Item buildItem (String name) {
		this.item = new Item (name, initialQuality, initialSellIn);
		return this.item;
	}

	private GildedRose getGildedRose () {
		return new GildedRose ();
	}
	
	private List<Item> buildList (Item item) {
		List<Item> items = new ArrayList<> ();
		items.add(item);
		return items;
	}
}
