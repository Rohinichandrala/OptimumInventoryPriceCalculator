package com.charo.data;

import static com.charo.data.StoreConstants.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InventoryStore {
	private String storeLocation;
	private Map<String,Inventory> inventoryList=new HashMap<String,Inventory>();
	private static List<InventoryStore> list=new LinkedList<InventoryStore>();
	private static InventoryStore BRAZIL_INVENTORY;
	private static InventoryStore ARGENTINA_INVENTORY;
	public InventoryStore(String storeLocation,Inventory ... inventories)
	{
		this.storeLocation=storeLocation;
		for (int i = 0; i < inventories.length; i++) {
			inventoryList.put(inventories[i].getItemName().toUpperCase(), inventories[i]);
		}	
		list.add(this);
	}
	public static void initializeBrazilInventory(){
	BRAZIL_INVENTORY=new InventoryStore(
			"BRAZIL",new Inventory(ITEM1,BRAZIL_ITEM1_COST,BRAZIL_ITEM1_COUNT),
			new Inventory(ITEM2,BRAZIL_ITEM2_COST,BRAZIL_ITEM2_COUNT)
			);
	}
	public static void initializeArgentinaInventory(){
	ARGENTINA_INVENTORY=new InventoryStore(
			"ARGENTINA",new Inventory(ITEM1,ARGENTINA_ITEM1_COST,ARGENTINA_ITEM1_COUNT),
			new Inventory(ITEM2,ARGENTINA_ITEM2_COST,ARGENTINA_ITEM2_COUNT)
			);
	}
	public String getStoreLocation()
	{
		return storeLocation;
	}
	public Map<String,Inventory> getInventoryList()
	{
		return this.inventoryList;
	}
	public static List<InventoryStore> getInventoryStoreList()
	{		
		return list;
	}
	public static InventoryStore getInventoryStore(String name)
	{
		for(InventoryStore store:getInventoryStoreList())
		{
			if(store.getStoreLocation().equalsIgnoreCase(name))
				return store;
		}
		return null;
	}

}
