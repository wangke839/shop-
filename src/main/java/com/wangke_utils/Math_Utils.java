package com.wangke_utils;

import java.util.regex.Pattern;

public class Math_Utils {

	public static boolean isNumber(String str){
		String reg = "\\d{1,}";
		return str.matches(reg);
	}
}
