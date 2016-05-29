package com.charo.services;

import static com.charo.data.InventoryStore.*;

import java.util.Map.Entry;

import com.charo.data.InventoryStore;
import com.charo.forms.User_Order;


public class OptPriceCalculator {
	private User_Order user_order;
	private boolean stockAvailable=true;
	private int order_amount=0;
	private final int TRANSPORTATION_FARE=400;
	private final int TRANSPORTATION_DISCOUNT=20;
	private final int TRANSPORTATION_DIS_FARE=TRANSPORTATION_FARE-((TRANSPORTATION_FARE)*TRANSPORTATION_DISCOUNT)/100;
	private int temp_count=0;

	static
	{
		initializeStores();
	}
	public OptPriceCalculator(User_Order user_order)
	{
		this.user_order=user_order;
		checkStock();
		calculate();
	}
	public static void initializeStores()
	{
		initializeBrazilInventory();
		initializeArgentinaInventory();
	}
	public void checkStock()
	{
		for(Entry<String, Integer> entry:user_order.getOrderItems().entrySet())
		{
			int item_count=0;
		for(InventoryStore store:getInventoryStoreList())
		{
			item_count+=store.getInventoryList().get(entry.getKey()).getItemCount();
		}
		if(item_count<entry.getValue())
			stockAvailable=false;
		}
	}
	public boolean checkTransportation(String storeLocation)
	{
		if(!user_order.getUserLocation().equalsIgnoreCase(storeLocation))
			return true;
		
		return false;
	}
	
	private void calculate()
	{
		if(stockAvailable){
		for(Entry<String, Integer> entry:user_order.getOrderItems().entrySet())
		{	
			temp_count=entry.getValue();
			if(entry.getValue()%10!=0)
			{
				System.out.println("Inside if condition");
				prepareBill(getInventoryStore(user_order.getUserLocation()),entry.getKey(),entry.getValue()%10);
			}
			while(temp_count>0)			
					prepareBill(getMinStore(entry.getKey(),temp_count),entry.getKey(),temp_count);				
		}
		displayResult();
		}
		else
			System.out.println("***********OUT OF STOCK************\n Sorry!!! Stores can't serve your request");
	}
	public void displayResult()
	{
		String result=order_amount+"";
		for(Entry<String, Integer> entry:user_order.getOrderItems().entrySet())
		{
			result+=":"+entry.getKey();
		for(InventoryStore store:getInventoryStoreList())
		{
			
			result+=":"+store.getInventoryList().get(entry.getKey()).getItemCount();
		}
		}
		System.out.println(result);
	}
	// for this method, check how you can avoid this.. check how you can prepare Bill while calling MinStore() itself
	private void prepareBill(InventoryStore store,String item_type,int count)
	{
		int item_count=store.getInventoryList().get(item_type).getItemCount();
		if(item_count<count)
		{
			/*calculate order only in terms of 10's, bcoz transport cost is always in multiples of 10 units
			 * consider the case of 125--- 25 can't be shipped from Argentina as transportation cost for 5 items is lost
			 * so ship 30 instead.
			 * */		
			order_amount+=(item_count-item_count%10)*store.getInventoryList().get(item_type).getItemPrice();
			if(checkTransportation(store.getStoreLocation()))
				order_amount+=getTransportationCost(store.getStoreLocation(),(item_count-item_count%10));
			temp_count=temp_count-(item_count-item_count%10);
			store.getInventoryList().get(item_type).setItemCount(item_count-(item_count-item_count%10));
		}
		else
		{
		order_amount+=(count)*store.getInventoryList().get(item_type).getItemPrice();
		if(checkTransportation(store.getStoreLocation()))
		{
			order_amount+=getTransportationCost(store.getStoreLocation(),(count));
		}
		temp_count=temp_count-count;
		store.getInventoryList().get(item_type).setItemCount(item_count-count);
		}
	}
	
	private int getTransportationCost(String store_location,int units)
	{
		if(checkTransportation(store_location))
		{
			if(user_order.getNationality()!=null && user_order.getNationality().equalsIgnoreCase(store_location))
				return (units/10)*TRANSPORTATION_DIS_FARE;
			else 
				return (units/10)*TRANSPORTATION_FARE;
		}
		return 0;
	}
	private InventoryStore getMinStore(String item_type, int count)
	{
		int cost=Integer.MAX_VALUE;
		InventoryStore optStore=null;
		for(InventoryStore store:getInventoryStoreList())
		{
			if(store.getInventoryList().get(item_type).getItemCount()>=10)
			{
			int temp_cost=store.getInventoryList().get(item_type).getItemPrice()*count;
			if(checkTransportation(store.getStoreLocation()))
				temp_cost+=getTransportationCost(store.getStoreLocation(), count);
			if(cost>temp_cost)
			{
				optStore=store;
				cost=temp_cost;
			}
			}
		}
		return optStore;
	}
}
