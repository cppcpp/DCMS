package com.nxdcms.action;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.nxdcms.entity.PageObject;
import com.nxdcms.entity.Subcompetition;
import com.opensymphony.xwork2.Action;

import utils.HibernateUtils1;
import utils.LimitDao;

public class ShowLimitAction implements Action {
	
	//获取表单的值
	private String pageSize ;
	private String curPage ;
	private String grade ;
	private String teacher;
	private String time ;
	private PageObject result = null;

	@Override
	public String execute() throws Exception {
		//调试使用
		System.out.println("当前页"+curPage+"每页行数"+pageSize+grade+teacher+time);

		//定义查询条件
		Criterion criterion0=null,criterion1=null,criterion2=null;
		
		//创建hibernate的session
		Session session = HibernateUtils1.getSession();

		//定义hibernate所要查询类
		Class ObjClass = null;
		try {
			//实例化要查询的类名
			ObjClass = Class.forName("com.nxdcms.entity.Subcompetition");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//过滤查询条件，没有查询条件就将空值传入方法
		if(grade!=null&&!"".equals(grade)&&!"null".equals(grade)){
			criterion0 = Restrictions.like("awardGrade", grade);
		}else{
			System.out.println("grade null");
		}
		if(teacher!=null&&!"".equals(teacher)&&!"null".equals(teacher)){
			criterion1 = Restrictions.like("teacher",teacher);
		}else{
			System.out.println("teacher null");
		}
		if(time!=null&&!"".equals(time)&&!"null".equals(time)){
			criterion2 = Restrictions.like("awarddate", time);
		}else{
			System.out.println("time null");
		}
		
		//定义排序（降序/升序）
		Order order = Order.asc("stuId1");
		
		//参数传入工具，返回一个具体的分页类
		//注意最后三个查询条件可以不止三个，理论可以传入无限多个查询条件
		result = LimitDao.queryByPage(session, Integer.parseInt(pageSize), Integer.parseInt(curPage), ObjClass, order,
				criterion0, criterion1, criterion2);
		
		//调试使用
		if (result == null) {
			System.out.println("po null");
		} else {
			for (Object o : result.getList()) {
				Subcompetition s = (Subcompetition) o;
				System.out.println(s.getAwardGrade());
			}
		}
		System.out.println(result);
		
		return "success";
	}
	
	
	//测试函数
	@Test
	public void test() {
		Session session = HibernateUtils1.getSession();
		Class ObjClass = null;
		try {
			ObjClass = Class.forName("com.nxdcms.entity.Subcompetition");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Order order = Order.asc("stuId1");
		Criterion criterion0 = Restrictions.like("awardGrade", grade);
		Criterion criterion1 = Restrictions.like("teacher", teacher);
		result = LimitDao.queryByPage(session, Integer.parseInt(pageSize), Integer.parseInt(curPage), ObjClass, order,
				criterion0, criterion1);
		if (result == null) {
			System.out.println("po null");
		} else {
			for (Object o : result.getList()) {
				Subcompetition s = (Subcompetition) o;
				System.out.println(s.getAwardGrade());
			}
		}
		System.out.println(result);
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

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public PageObject getResult() {
		return result;
	}

	public void setResult(PageObject result) {
		this.result = result;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}


}
