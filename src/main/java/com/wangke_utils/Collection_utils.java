package com.wangke_utils;

import java.util.Collection;

public class Collection_utils {

	public static boolean isEmpty(Collection<?> src){
		int i = src.size();
		if( i == 0 || src == null){
			return true;
		}
		return false;
		
	}
}
