package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AutoLoginFilter implements Filter{
	public void destroy() {
		System.out.println("Filter    destroy....");
	}
	public void doFilter(ServletRequest Srequest, ServletResponse Sresponse, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter starting...");
		HttpServletRequest request=(HttpServletRequest) Srequest;
		HttpServletResponse response=(HttpServletResponse) Sresponse;
		String uri=request.getRequestURI();
		String stuId=(String) request.getSession().getAttribute("stuId");
		String adminId=(String) request.getSession().getAttribute("adminId");
		
		System.out.println("Dofilter:stuId:"+stuId+"+++adminId:"+adminId);
		System.out.println("访问路径："+uri);
		
		
		//已登录，不允许访问登录页面，未登录，不允许访问个人中心
		if(stuId!=null||adminId!=null){
			
			if(uri.equals("/dcms/Student_login.jsp")){
				System.out.println("已登录，不允许访问登录页面");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			chain.doFilter(Srequest, Sresponse);
			return;
			
		}else{
			//未登录，允许访问的页面以及action
			if(uri.equals("/dcms/")||uri.equals("/dcms/index.jsp")
					||uri.equals("/dcms/news.jsp")
					||uri.equals("/dcms/Competition-anonymous.jsp")
					||uri.equals("/dcms/showquery.jsp")
					||uri.equals("/dcms/query.jsp")
					||uri.equals("/dcms/Student_regist.jsp")
					||uri.equals("/dcms/queryNews.action")
					||uri.equals("/dcms/anonymousQuery.action")
					||uri.equals("/dcms/searchNews.action")
					||uri.equals("/dcms/check.action")
					||uri.equals("/dcms/listc.action")
					||uri.equals("/dcms/indexshowNews.action")
					||uri.equals("/dcms/createCode.action")
					||uri.equals("/dcms/newsContent.action")
					||uri.equals("/dcms/likesearch.action")
					||uri.equals("/dcms/export.action")
					||uri.equals("/dcms/content.action") 
					||uri.equals("/dcms/login.action")
					||uri.equals("/dcms/stuIdCheck.action")
					||uri.equals("/dcms/codeCheck.action")
					||uri.equals("/dcms/comPaging.action")
					||uri.equals("/dcms/limit.action")
					||uri.equals("/dcms/noticelimit.action")
					||uri.equals("/dcms/newslimit.action")
					||uri.equals("/dcms/comPaging.action")
					
					){
				chain.doFilter(Srequest, Sresponse);
			}else{
				System.out.println("无权限访问");
				request.getRequestDispatcher("Student_login.jsp").forward(request, response);
			}
			
			
		}
	
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter initing");
	}
	
}
