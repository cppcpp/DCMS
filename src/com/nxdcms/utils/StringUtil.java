package com.nxdcms.utils;

import java.util.ArrayList;
import java.util.Collection;

public class StringUtil {
	public static String connectString(Collection<String> stringList,String split){
		boolean flag = false;
		StringBuilder sb = new StringBuilder();
		for (String string : stringList) {
			
			if(flag == true){
				sb.append(split);
			}else{
				flag = true;
			}
				sb.append(string);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ArrayList<String> stringList = new ArrayList<>();
		stringList.add("zsd");
		stringList.add("lis");
		stringList.add("wangwu");
		System.out.println(connectString(stringList, "|"));
	}
}
