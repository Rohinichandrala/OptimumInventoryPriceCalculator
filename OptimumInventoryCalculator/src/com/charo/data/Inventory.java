package com.charo.data;

import com.charo.exceptionhandlers.InvalidUserDetailsException;

public class Inventory {
	private String itemName="no product";
	private int itemPrice=0;
	private int itemCount=0;
	public Inventory(String itemName, int itemPrice, int itemCount)
	{
		this.itemName=itemName;
		this.itemPrice=itemPrice;
		this.itemCount=itemCount;
	}
	public String getItemName() {
		return itemName;
	}
	
	public int getItemPrice() {
		return itemPrice;
	}
	
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount)
	{
		this.itemCount=itemCount;
	}
}
