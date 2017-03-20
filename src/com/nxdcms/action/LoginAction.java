package com.nxdcms.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import utils.CodeUtil;
import utils.MyCookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.nxdcms.entity.Administrator;
import com.nxdcms.entity.Student;
import com.nxdcms.service.StudentService;
import com.nxdcms.service.impl.AdministratorServiceImpl;
import com.nxdcms.service.impl.StudentServiceImpl;

public class LoginAction extends BaseAction{
	
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request= ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	private ActionContext ctx;
	private Administrator administrator;
	private Student student;
	
	//the front input name
	private String Id;
	private String Password;
	private String Code;
	private String rememberMe;
	
	/*private String administratorId;
	private String administratorPassword;
	private String stuId;
	private String stuPassword;*/
	MyCookie myCookie=new MyCookie();
	StudentService studentService;
	StudentServiceImpl ssi;
	AdministratorServiceImpl asi;
	List<Administrator> list=new ArrayList<Administrator>();
	Map<String,Object> map = new HashMap<String,Object>();
	
	


	public String getId() {
		return Id;
	}
	public void setId(String id) {
		this.Id = id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		this.Code = code;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	@Override
	public HttpServletResponse getResponse() {
		return response;
	}
	@Override
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public Administrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public String getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
	public List<Administrator> getList() {
		return list;
	}
	public void setList(List<Administrator> list) {
		this.list = list;
	}
	
	
	
	/*
	 * 登录成功后，session.setArrribute("stuId",Id)   session.setAttribute("AdminId",Id)
	 * */
	//Login Check
	public String Login(){
		ssi = new StudentServiceImpl();
		asi=new AdministratorServiceImpl();
		session.setAttribute("loginfail", "");
		session.setAttribute("codeWrong", "");
		
		System.out.println("The front page data：Id:"+Id+"+Password:"+Password+"+Code:"+Code+"+rememberMe:"+rememberMe);
		
		//the number of visitors to the site
		/*Integer counter=(Integer) ctx.getApplication().get("counter");
		if(counter==null){
			counter=1;
		}else{
			counter++;
		}
		ctx.getApplication().put("counter", counter);*/
		
		
		//search the Id and the Password in student table
		student = ssi.searchStu(Id,Password);
		//search the Id and the Password in administrator table
		administrator=asi.searchAdm(Id, Password);
		System.out.println("I am administrator:"+administrator+"      or student:"+student);
		
		//是否在不同浏览器上重复登录
		if(canLogin(Id,request)){
			//验证码
			String rand=(String)session.getAttribute("rand");
			System.out.println("正确的验证码："+rand);
			//忽略验证验证码的大小写
			if(Code.toUpperCase().equals(rand.toUpperCase())){
				//如果是学生
				if(student!=null){	
					if(canLogin(Id,request)){
						
						if(rememberMe!=null){
							Cookie cookie= myCookie.addCookie(Id, Password);
							response.addCookie(cookie);
						}else{
							//remember me is null
							Cookie cookie= myCookie.removeCookie(request);
							if(cookie!=null){
								response.addCookie(cookie);
							}
						}
						
						String name=student.getStudentName();
						//将学生姓名放进session中
						session.setAttribute("stuMess", name+"，欢迎来到学科管理系统");
						//将学生Id放进session中
						session.setAttribute("stuId", student.getStudentId());
						
						
						session.setAttribute("codeWrong", "");
						session.setAttribute("loginfail", "");
						return "studentLogin";
					}
				} 
				//如果是管理员
				if(administrator!=null){
					if(canLogin(Id,request)){
						
						if(rememberMe!=null){
							Cookie cookie= myCookie.addCookie(Id, Password);
							response.addCookie(cookie);
						}else{
							//remember me is null
							Cookie cookie= myCookie.removeCookie(request);
							if(cookie!=null){
								response.addCookie(cookie);
							}
						}
						
						String name=administrator.getAdministratorName();
						session.setAttribute("Admin", administrator);
						session.setAttribute("adminId", administrator.getAdministratorId());
						session.setAttribute("adminName", name);
						
						session.setAttribute("codeWrong", "");
						session.setAttribute("loginfail", "");
						return "adminLogin";
					}
				}
				
				session.setAttribute("loginfail", "用户名或密码错误");
				session.setAttribute("codeWrong", "");
				return "loginfail";
			}else{
				//code is wrong
				session.setAttribute("codeWrong", "验证码错误，请重新输入");
				session.setAttribute("loginfail", "");
				return "codeWrong";
			}
		}else{
			return "LoginAgain";
		}
		
	}

	
	//判断不同浏览器上同一用户登录情况
	//用唯一的sessionId和studentId判断，放在map中
	//通过stuId取sessionId，如果sessionId为空，放入map，不为空，判断是否和当前sessionId相同，
	public boolean canLogin(String stuId,HttpServletRequest request){
		HttpSession session=request.getSession();
		Map<String,String> userSessionMap=null;
		//获取全局域
		ServletContext application=session.getServletContext();
		userSessionMap=(Map)application.getAttribute("userSessionMap");
		if(userSessionMap==null){
			userSessionMap=new HashMap<String,String>();
			userSessionMap.put(stuId,session.getId());
			application.setAttribute("userSessionMap", userSessionMap);
			return true;
		}
		String sessionId=userSessionMap.get(stuId);
		if(sessionId==null){
			userSessionMap.put(stuId,session.getId());
			return true;
		}
		if(!sessionId.equals(session.getId())){
			request.setAttribute("loginAgainMessage", "您的账号已在另一浏览器登录！");
			return false;
		}		
		
		return true;
	}
	 //Logout
    public String logout() {  
    	
    	ServletContext application=session.getServletContext();
    	Object objStu = session.getAttribute("stuId");
    	Object objAdmin=session.getAttribute("AdminId");
    	
    	if(objStu!=null){
    		session.invalidate();
    		application.removeAttribute("userSessionMap");
    		return "logoutStu";
    	}
    	if(objAdmin!=null){
    		session.invalidate();
    		return "logoutAdmin";
    	}
		return "error";
    }
    //����ѧ����Ϣ
    public String perfectStuInfo() throws UnsupportedEncodingException{
    	studentService=new StudentServiceImpl();
    	//System.out.println("执行查询学生信息的方法-------");
    	//System.out.println("学生的ID：：："+student.getStudentId());
    	if(studentService.perfectStuInfo(student)){
    		return Action.SUCCESS;
    	}else{
    		return Action.ERROR;
    	}
    }

    public void showStuInfo() throws IOException{
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	response.setContentType("text/html:utf-8");
    	
    	ssi = new StudentServiceImpl();
    	HttpSession session=ServletActionContext.getRequest().getSession();
    	String stuId=(String) session.getAttribute("stuId");
    	Student student=ssi.searchStu(stuId);
 
    	//后台从hibernate取出的student是存在session
    	//直接传map格式不正确
    	map.put("student",student);
    	
    	/*JSONArray json=JSONArray.fromObject(map);
    	System.out.println(json);*/
    	JsonConfig config=new JsonConfig();
    	String rs=JSONObject.fromObject(map,config).toString();
    	
    	PrintWriter out=response.getWriter();
    	out.print(rs);
    	//第二种方式：返回void，将map放在response的输出流中。
    	//原因：对象的元素也为对象时，，传到前台时报错，，内存溢出
    } 	
    
    //check and change student password
    public void StuPasswordcheck() throws IOException{
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html; charset=UTF-8");
		
		Student student=null;
		ssi=new StudentServiceImpl(); 
		PrintWriter out=response.getWriter();
		String stuId=(String) request.getSession().getAttribute("stuId");
		System.out.println("studnetId:"+stuId);
		String oldPass=request.getParameter("oldPass");
		String newPass=request.getParameter("newPass");
		String confirmPass=request.getParameter("confirmPass");
		String result="";
		boolean flag=true; 
		System.out.println(stuId+"   "+oldPass+"    "+newPass);
		
		student=ssi.searchStu(stuId, oldPass);
		if(student==null){
			//out.print("密码错误！\n");
			result+="密码错误,请重新输入！\n";
			flag=false;
			out.print(result);
		}
		
		if(flag==true){
			boolean isEqual= ssi.modifyStuPassword(student, newPass);
			System.out.println("equal:"+isEqual);
			if(isEqual==true){
				out.print("修改密码成功！");
			}else{
				out.print("修改密码失败！");
			}
		}
	}    	
    	
    public String registStudent() throws UnsupportedEncodingException{
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html; charset=UTF-8");
		
		ActionContext act=ActionContext.getContext();
		//System.out.println("student++++ID������"+student.getStuId());
		
		ssi=new StudentServiceImpl();
		if(ssi.addStu(student)){
			System.out.println("增加学生成功");
			return Action.SUCCESS;
		}else{
			System.out.println("增加学生失败");
			return Action.ERROR;
		}		
	}
    
    public void createCode(){
    	CodeUtil codeUtil=new CodeUtil();
    	codeUtil.createCode(request,response);
    }
    
    public void codeCheck(){
		try {
			PrintWriter out=response.getWriter();
	    	String pic=request.getParameter("rand");
	    	String rand=(String) request.getSession().getAttribute("rand");
			System.out.println("pic:"+pic+"+++++++rand"+rand);
			if(pic.equals(rand) || rand.equals("")){
				out.print("true");
			}else{
				out.print("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}   	
    }
    
    public void stuIdCheck() throws IOException{
    	System.out.println("执行");
    	PrintWriter out=response.getWriter();
    	String stuId=request.getParameter("stuId");
		System.out.println("STUID::::"+stuId);
		StudentServiceImpl  ssi = new StudentServiceImpl();
		student = ssi.searchStu(stuId);
		if(student!=null){
			out.print("exit");
		}else{
			out.print("noexit");
		}
    }
}
