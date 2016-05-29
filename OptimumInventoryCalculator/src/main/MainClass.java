package main;

import java.util.Scanner;

import com.charo.forms.OrderForm;

public class MainClass {

	
	public static void main(String args[])
	{
		System.out.println("********Welcome to Store***********");
		System.out.println("Please enter input in the format"
				+ "<purchase_country>:<optional_passport_number>:<item_type>:<number_of_units_to_be_ordered>:<item_type>:<number_of_units_to_be_ordered>");
		System.out.println("Sample input can be BRAZIL:B123AB1234567:IPHONE:20:IPOD:10");
		System.out.println("Please enter your order\n ");
		Scanner sc=new Scanner(System.in);
		OrderForm form=new OrderForm();
		form.createForm(sc.next());
		
	}
}
