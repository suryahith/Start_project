package com.zoho.testcases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temp {

	public static void main(String[] args) throws ParseException {
		Date d  = new Date();
		System.out.println(d);
		
		String date="3/04/2105";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = sdf.parse(date);
		System.out.println(d.compareTo(d1));
		
		sdf = new SimpleDateFormat("d");
		System.out.println(sdf.format(d1));
		
	}

}
