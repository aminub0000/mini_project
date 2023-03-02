package com.example.servlets;

public class Staticv {
	   private static String name;
	   private static String password;

	   public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Staticv.password = password;
	}

	public static String getName() {
	      return name;
	   }

	   public static void setName(String value) {
		   Staticv.name = value;
	   }
}
