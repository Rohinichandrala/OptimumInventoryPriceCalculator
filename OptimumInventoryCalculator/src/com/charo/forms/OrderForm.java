package com.charo.forms;

import com.charo.exceptionhandlers.InvalidUserDetailsException;
import com.charo.forms.User_Order;
import com.charo.services.OptPriceCalculator;
public class OrderForm {

	public void createForm(String input)
	{
		try{
			User_Order order=new User_Order(input);
			new OptPriceCalculator(order);
		}
		catch(InvalidUserDetailsException e)
		{
			System.out.println(e);
		}
		// if possible add some code to give the user other chance to enter correct inputs
	}
	}
