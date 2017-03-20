package com.nxdcms.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.nxdcms.dao.impl.Newsdaoimpl;
import com.nxdcms.entity.News;
import com.nxdcms.entity.PageObject;
import com.nxdcms.service.impl.Newsserviceimpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import utils.HibernateUtils1;
import utils.LimitDao;

public class NewsAction extends ActionSupport {

	private News news;
	private String message;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String pageSize ;
	private String curPage ;
	private String newsTitle;
	private String newsIssuedate;
	private String newsIssuer;
	private PageObject result = null;
	public PageObject getResult() {
		return result;
	}

	public void setResult(PageObject result) {
		this.result = result;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsIssuedate() {
		return newsIssuedate;
	}

	public void setNewsIssuedate(String newsIssuedate) {
		this.newsIssuedate = newsIssuedate;
	}

	public String getNewsIssuer() {
		return newsIssuer;
	}

	public void setNewsIssuer(String newsIssuer) {
		this.newsIssuer = newsIssuer;
	}

	Newsserviceimpl usi = new Newsserviceimpl();
	Newsdaoimpl udi = new Newsdaoimpl();
	List<News> list = new ArrayList<News>();
	
	private String newsPhotoFileName;
	private File newsPhoto;
	private String newsPhotoContentType;
	
	public String getNewsPhotoFileName() {
		return newsPhotoFileName;
	}

	public void setNewsPhotoFileName(String newsPhotoFileName) {
		this.newsPhotoFileName = newsPhotoFileName;
	}

	public File getNewsPhoto() {
		return newsPhoto;
	}

	public void setNewsPhoto(File newsPhoto) {
		this.newsPhoto = newsPhoto;
	}

	public String getNewsPhotoContentType() {
		return newsPhotoContentType;
	}

	public void setNewsPhotoContentType(String newsPhotoContentType) {
		this.newsPhotoContentType = newsPhotoContentType;
	}

	public List<News> getList() {
		return list;
	}

	public void setList(List<News> list) {
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String add() throws Exception {
		InputStream is = null;
		OutputStream os = null;
		ActionContext ac =  ActionContext.getContext();
		ServletContext sContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String path = sContext.getRealPath("/");
		String mypath = path+"\\file\\";
		
		try {
			if(newsPhoto != null){
				is = new BufferedInputStream(new FileInputStream(newsPhoto));
				os = new BufferedOutputStream(new FileOutputStream(mypath + newsPhotoFileName)); 
				byte[] buffer = new byte[1024];
				int len =0;
				while((len = is.read(buffer))>0){
					os.write(buffer,0,len);
				}
				news.setNewsPhotoName(newsPhotoFileName);
				news.setNewsPhotoPath(mypath + newsPhotoFileName);
			}
		} finally {
			if(is != null){ is.close(); }
			if(os != null){ os.close(); }
		}	
		if (usi.addNews(news)) {
			message = "添加用户成功";

			return Action.SUCCESS;
		} else {
			message = "添加用户失败";
			return Action.ERROR;
		}
	}

	public String del() throws Exception {
		if (usi.delNews(news)) {
			message = "删除用户成功";

			return Action.SUCCESS;
		} else {
			message = "删除用户失败";
			return Action.ERROR;
		}
	}

	public String update() throws Exception {
		InputStream is = null;
		OutputStream os = null;
		ActionContext ac =  ActionContext.getContext();
		ServletContext sContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String path = sContext.getRealPath("/");
		String mypath = path+"\\file\\";
		try {
			if(newsPhoto != null){
				is = new BufferedInputStream(new FileInputStream(newsPhoto));
				os = new BufferedOutputStream(new FileOutputStream(mypath + newsPhotoFileName)); 
				byte[] buffer = new byte[1024];
				int len =0;
				while((len = is.read(buffer))>0){
					os.write(buffer,0,len);
				}
				news.setNewsPhotoName(newsPhotoFileName);
				news.setNewsPhotoPath(mypath + newsPhotoFileName);
			}
		} finally {
			if(is != null){ is.close(); }
			if(os != null){ os.close(); }
		}
		if (usi.modifyNews(news)) {
			message = "修改用户成功";

			return Action.SUCCESS;
		} else {
			message = "修改用户失败";
			return Action.ERROR;
		}

	}

	
	public String search() throws Exception {
		System.out.println(news.getNewsTitle());
		this.setList(udi.Query(news));
		System.err.println("++++++++Query++++++++"+udi.Query(news).toString());
		return Action.SUCCESS;

	}
	
	public String list() throws Exception {
		this.setList(udi.list());
		
		return Action.SUCCESS;

	}
	public String details() throws Exception {
		System.out.println(news.getNewsTitle());
		this.setList(usi.showDetails(news));
		System.err.println("++++++++Query++++++++"+udi.Details(news).toString());
		return Action.SUCCESS;
	}
	
	public String show() throws Exception {
		this.setList(usi.showNews());
		return Action.SUCCESS;
	}
	
	public String showIndex() throws Exception {
		this.setList(usi.showIndexnews());
		System.err.println("------ShowIndex---------"+udi.ShowIndex().toString());
		return Action.SUCCESS;
	}
	
	public String limit() throws Exception {
		//调试使用
		System.out.println("当前页"+curPage+"每页行数"+pageSize+newsTitle+newsIssuedate+newsIssuer);

		//定义查询条件
		Criterion criterion0=null,criterion1=null,criterion2=null;
		
		//创建hibernate的session
		Session session = HibernateUtils1.getSession();

		//定义hibernate所要查询类
		Class ObjClass = null;
		try {
			//实例化要查询的类名
			ObjClass = Class.forName("com.nxdcms.entity.News");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//过滤查询条件，没有查询条件就将空值传入方法
		if(newsTitle!=null&&!"".equals(newsTitle)&&!"null".equals(newsTitle)){
			System.out.println(newsTitle);
			criterion0 = Restrictions.like("newsTitle", newsTitle);
		}else{
			System.out.println("newsTitle null");
		}
		if(newsIssuedate!=null&&!"".equals(newsIssuedate)&&!"null".equals(newsIssuedate)){
			criterion1 = Restrictions.like("newsIssuedate",newsIssuedate);
		}else{
			System.out.println("newsIssuedate null");
		}
		if(newsIssuer!=null&&!"".equals(newsIssuer)&&!"null".equals(newsIssuer)){
			criterion2 = Restrictions.like("newsIssuer", newsIssuer);
		}else{
			System.out.println("newsIssuer null");
		}
		
		//定义排序（降序/升序）
		Order order = Order.asc("newsTitle");
		
		//参数传入工具，返回一个具体的分页类
		//注意最后三个查询条件可以不止三个，理论可以传入无限多个查询条件
		result = LimitDao.queryByPage(session, Integer.parseInt(pageSize), Integer.parseInt(curPage), ObjClass, order,
				criterion0, criterion1, criterion2);
		
		//调试使用
		if (result == null) {
			System.out.println("po null");
		} else {
			for (Object o : result.getList()) {
				News s = (News) o;
				System.out.println(s.getNewsTitle());
			}
		}
		System.out.println(result);
		
		return "success";
	}

}
