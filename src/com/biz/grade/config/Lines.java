package com.biz.grade.config;

public class Lines {

	public static String D_LINE = "";
	public static String S_LINE = "";
	
	static {
		for(int i =0; i < 120 ; i++) {
			D_LINE += "=";
			S_LINE += "-";
		}
		
	}
	
}
