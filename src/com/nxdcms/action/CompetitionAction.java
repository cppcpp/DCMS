package com.nxdcms.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.nxdcms.dao.impl.*;
import com.nxdcms.entity.Competition;
import com.nxdcms.entity.PageObject;
import com.nxdcms.entity.Subcompetition;
import com.nxdcms.service.CompetitionManage;
import com.nxdcms.service.CompetitionService;
import com.nxdcms.service.impl.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import utils.HibernateUtils;
import utils.LimitDao;

public class CompetitionAction extends ActionSupport{
	private Competition competition;
	private HttpServletRequest request =ServletActionContext.getRequest() ;
	private HttpServletResponse response = ServletActionContext.getResponse();
	//做模糊查询时获取的竞赛名称，时间
	
	private Date startTime;
	private Date stopTime;
	List<Competition> list=new ArrayList<Competition>();
	List<Subcompetition> sublist=null;
	private File comLink;
	private String comLinkFileName;
	
	//分页查询
	//获取表单的值
	private String pageSize ;
	private String curPage ;
	private String comName; 
	private String scale;
	private String type;
	private	String flag;
	private PageObject result = null;
	
	
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
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

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Competition> getList() {
		return list;
	}

	public void setList(List<Competition> list) {
		this.list = list;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public File getComLink() {
		return comLink;
	}

	public void setComLink(File comLink) {
		this.comLink = comLink;
	}


	public String getComLinkFileName() {
		return comLinkFileName;
	}

	public void setComLinkFileName(String comLinkFileName) {
		this.comLinkFileName = comLinkFileName;
	}

	//保存竞赛信息
	public String SaveCompetitionInfo() throws Exception{
		CompetitionService csi=new CompetitionServiceImpl();
		InputStream is = null;
		OutputStream os = null;
		ActionContext ac =  ActionContext.getContext();
		ServletContext sContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String path = sContext.getRealPath("/");
		System.out.println("path:"+path);
		String mypath=path+"file\\";
		System.out.println("mypath1:"+mypath);
		
		try {
			if (comLink != null) {
				is = new BufferedInputStream(new FileInputStream(comLink));
				os = new BufferedOutputStream(new FileOutputStream(mypath + comLinkFileName));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) > 0) {
					os.write(buffer, 0, len);
				}
				competition.setComLink(comLinkFileName);
				competition.setComLinkPath(mypath + comLinkFileName);							
				//System.out.println("getComLinkPath()："+competition.getComLinkPath());
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}	
		if (csi.SaveComInfo(competition)){
				return Action.SUCCESS;
		}else{
			return Action.ERROR;
		}
	}
	
	public PageObject getResult() {
		return result;
	}

	public void setResult(PageObject result) {
		this.result = result;
	}

	//删除竞赛信息
	public String DeleteCompetitionInfo() throws Exception{
		CompetitionService csi=new CompetitionServiceImpl();

		if (csi.DeleteComInfo(competition)){
			return Action.SUCCESS;
		}else{
			return Action.ERROR;
		}
	}
	
	//修改竞赛信息
	public String ModifyCompetitionInfo() throws Exception{
		CompetitionService csi=new CompetitionServiceImpl();
		InputStream is = null;
		OutputStream os = null;
		//String mypath = "C:\\apache-tomcat-7.0.69-windows-x64\\apache-tomcat-7.0.69\\webapps\\competion\\comLink\\";
		ActionContext ac =  ActionContext.getContext();
		ServletContext sContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String path = sContext.getRealPath("/");
		System.out.println("path:"+path);
		String mypath=path+"file\\";
		try {
			if(comLink != null){
				is = new BufferedInputStream(new FileInputStream(comLink));
				os = new BufferedOutputStream(new FileOutputStream(mypath + comLinkFileName)); 
				byte[] buffer = new byte[1024];
				int len =0;
				while((len = is.read(buffer))>0){
					os.write(buffer,0,len);
				}
				competition.setComLink(comLinkFileName);
				competition.setComLinkPath(mypath + comLinkFileName);
			}
		} finally {
			if(is != null){ is.close(); }
			if(os != null){ os.close(); }
		}
		
		if (csi.ModifyComInfo(competition)){
				return Action.SUCCESS;
		}else{
			return Action.ERROR;
		}
	}
	
	//查询所用竞赛信息列表
	public String QueryCompetitionInfo() throws Exception{	
		CompetitionService csi=new CompetitionServiceImpl();

		this.setList(csi.queryComInfo(competition));
		return Action.SUCCESS;
	}
	
	//按照comid查询某一竞赛详细信息
	public String QueryCompetitionInfo2() throws Exception{	
		CompetitionService csi=new CompetitionServiceImpl();
		CompetitionManage cm = new CompetitionManageImpl();
		
		ActionContext ctx=ActionContext.getContext();
		//设置网站的访问次数
		Integer counter=(Integer) ctx.getApplication().get("counter");
		if(counter==null){
			counter=1;
		}else{
			counter++;
		}
		ctx.getApplication().put("counter", counter);
		//System.out.println(counter);
		
		//查询competition和subCompetition信息
		this.setList(csi.querySpecificComInfo(competition));
		sublist = cm.queryclassifiedCompetition(competition);
		
		return Action.SUCCESS;
	}

	//模糊查询竞赛信息
	public String search() throws Exception{
		CompetitionService csi=new CompetitionServiceImpl();
		
		this.setList(csi.search(competition));
		return Action.SUCCESS;
	}

	//模糊查询竞赛信息（作查询参赛学生信息用）
	public String joinSearch() throws Exception{
		CompetitionService csi=new CompetitionServiceImpl();
		String comName=this.getComName();
		Date startTime=this.getStartTime();
		Date stopTime=this.getStopTime();
		System.out.println("模糊查询竞赛信息（作查询参赛学生信息用）"+comName+startTime+stopTime);
		this.setList(csi.joinSearch(comName, startTime, stopTime));
		return Action.SUCCESS;
	}
	
	
	//分页
	public String paging() throws Exception {
		/*request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html;UTF-8");*/  
		/*String newComName=null;
		String newScale=null;*/
		//调试使用
		System.out.println("当前页"+curPage+"每页行数"+pageSize);
		System.out.println("comname:"+comName+"type:"+type+"scale:"+scale+"flag:"+flag);
		/*if(comName!=null){
			newComName=new String(comName.getBytes("iso8859-1"),"utf-8");
		}
		if(scale!=null){
			newScale=new String(scale.getBytes("iso8859-1"),"utf-8");
		}*/
		
		//定义查询条件
		Criterion criterion0=null,criterion1=null,criterion2=null,criterion3=null;
		
		//创建hibernate的session
		Session session = HibernateUtils.getSession();

		//定义hibernate所要查询类
		Class ObjClass = null;
		try {
			//实例化要查询的类名
			ObjClass = Class.forName("com.nxdcms.entity.Competition");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//检查一个字符串既不是空串也不是null：----要先检查null，，
		//if(str!=null&&str.length()!=0)
		
		//过滤查询条件，没有查询条件就将空值传入方法
		//过滤查询条件，没有查询条件就将空值传入方法
		if(comName!=null&&!"".equals(comName)&&!"null".equals(comName)){
//			String newComName=new String(comName.getBytes("iso8859-1"),"utf-8");
//			System.out.println("newComName:"+newComName);
			criterion0 = Restrictions.like("comName", "%"+comName+"%");
		}else{
			System.out.println("comName null");
		}
		if(scale!=null&&!"".equals(scale)&&!"null".equals(scale)){
//			String newScale=new String(scale.getBytes("iso8859-1"),"utf-8");
//			System.out.println("newScale:"+newScale);
			criterion1 = Restrictions.like("scale","%"+scale+"%");
		}else{
			System.out.println("scale null");
		}
		if(String.valueOf(type)!=null&&!"".equals(String.valueOf(type))&&!"null".equals(String.valueOf(type))){
			criterion2 = Restrictions.eq("type", Integer.parseInt(type));
		}else{
			System.out.println("type null");
		}
		if(String.valueOf(flag)!=null&&!"".equals(String.valueOf(flag))&&!"null".equals(String.valueOf(flag))){
			criterion3 = Restrictions.eq("flag", Integer.parseInt(flag));
			
		}else{
			System.out.println("flag null");
		}
		
		//定义排序（降序/升序）
		Order order = Order.asc("comId");
		
		//参数传入工具，返回一个具体的分页类
		//注意最后三个查询条件可以不止三个，理论可以传入无限多个查询条件
		result = LimitDao.queryByPage(session, Integer.parseInt(pageSize), Integer.parseInt(curPage), ObjClass, order,
				criterion0,criterion1,criterion2,criterion3);
		
		//调试使用
		if (result == null) {
			System.out.println("po null");
		} else {
			System.out.println("result  size======"+result.getList().size());
			for (Object o : result.getList()) {
				Competition s = (Competition) o;
				System.out.println(s.getComName());
			}
		}
		System.out.println(result);
		
		return "success";
	}
	
}
