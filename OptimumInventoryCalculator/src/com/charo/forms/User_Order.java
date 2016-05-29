package com.charo.forms;

import java.util.HashMap;
import java.util.Map;

import com.charo.exceptionhandlers.InvalidUserDetailsException;
import com.charo.services.OptPriceCalculator;

public class User_Order {
	private String user_location="default";
	private String Nationality=null;
	private String passport=null;
	private Map<String,Integer> order_items=new HashMap<String,Integer>();
	private String[] tokens;
	
	
	public User_Order(String input)throws InvalidUserDetailsException
	{
		this.tokens=input.split(":");
		user_location=tokens[0];
		segregateItems();
		if(passport!=null)
			validatePassport();
	
		
	}
	public void segregateItems()
	{
		if(tokens.length==6)
		this.passport=tokens[1];
		else
			this.passport=null;
		for(int i=tokens.length-1;i>=tokens.length-4;i--)
		{
			switch(tokens[i-1].toUpperCase())
			{
			case "IPHONE":
				order_items.put("IPHONE",Integer.parseInt(tokens[i]));
				i--;
				break;
			case "IPOD":
				order_items.put("IPOD",Integer.parseInt(tokens[i]));
				i--;
				break;
			default:
				System.out.println("Couldn't add this item to list. No such products available");
				
			}
		}
	}
	
	public void validatePassport()throws InvalidUserDetailsException
	{
		switch(passport.charAt(0)+"".toUpperCase())
		{
		case "B":
			if(passport.length()==13)
			{
				if(!passport.toLowerCase().matches("^[b][0-9]{3}[a-z]{2}[a-z0-9]{7}$"))
					throw new InvalidUserDetailsException();
				else
					Nationality="BRAZIL";
			}
			else
				throw new InvalidUserDetailsException();
			break;
		case "A":
			if(passport.length()==12)
			{
				if(!passport.toLowerCase().matches("^[a][a-z]{2}[a-z0-9]{9}$"))
					throw new InvalidUserDetailsException();
				else
					Nationality="ARGENTINA";
			}
			else
				throw new InvalidUserDetailsException();

			break;
		default:
			throw new InvalidUserDetailsException();
		}
			
	}
	public String getUserLocation() {
		return user_location;
	}
	public String getPassport() {
		return passport;
	}
	public String getNationality()
	{
		return Nationality;
	}
	public Map<String,Integer> getOrderItems()
	{
		return order_items;
	}
}
