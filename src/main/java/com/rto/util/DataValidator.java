package com.rto.util;

import java.util.Date;



public class DataValidator {
	
	//Validate Name with regex.
	public static boolean isName(String val)
	{
		String name="[A-Za-z]*$";
		if(val.matches(name))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Validates Password
	public static boolean isPassword(String val)
	{
		String passregex="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12}$";
		if(val.matches(passregex))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Validates Phone no
	public static boolean isPhoneNo(String val)
	{
		String phoneregex="^[7-9][0-9]{9}$";
		if(val.matches(phoneregex))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Validates Null value
	public static boolean isNull(String val)
	{
		if (val == null || val.trim().length() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Validates Not Null Value
	public static boolean isNotNull(String val)
	{
		return !isNull(val);
	}
	//Validates Is Integer
	public static boolean isInteger(String val)
	{

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	//validates is Long 
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	//Validates Email
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	//Validates Dates
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}
	
	//Validates Registration Number
	public static boolean isRegNo(String val)
	{
		String regnoregex="^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$";
		if(val.matches(regnoregex))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
