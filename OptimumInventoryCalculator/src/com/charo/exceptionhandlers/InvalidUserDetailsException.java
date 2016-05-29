package com.charo.exceptionhandlers;

public class InvalidUserDetailsException extends Exception{
	public String toString()
	{
		return "You entered invalid location or Passport Details";
		
	}
}
