package com.nxdcms.interceptors;

import com.nxdcms.utils.RunUpdateDateBase;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class BackUpInterceptor implements Interceptor{
	@Override
	public void destroy() {
		
	} 

	@Override
	public void init() {
		Thread t = new Thread(new RunUpdateDateBase());
		t.start();
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {

		return arg0.invoke();
	}

}
