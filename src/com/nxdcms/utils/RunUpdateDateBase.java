package com.nxdcms.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 
 * @author ludi
 * 自动备份数据库
 */
public class RunUpdateDateBase implements Runnable{
	public static void main(String[] args) throws UnsupportedEncodingException {
		Thread t = new Thread(new RunUpdateDateBase());
		t.start();
	}

	private static void backupsData() throws UnsupportedEncodingException {
		URL resource = RunUpdateDateBase.class.getClassLoader().getResource("updatedateBase.bat");
		String path = resource.getPath();
		Runtime r = Runtime.getRuntime();
		path = URLDecoder.decode(path,"utf-8");
		try{
			r.exec(path); 
			Thread.sleep(3000);
			System.out.println("执行完毕");
		}catch(Exception e){ 
			System.out.println("错误:"+e.getMessage()); 
			e.printStackTrace(); 
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				backupsData();
				Thread.sleep(1000*60*60*24);
			} catch (InterruptedException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
