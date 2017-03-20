package com.nxdcms.dao;

import java.util.List;

import com.nxdcms.entity.Notice;



public interface NoticeDao {
	public boolean Add(Notice notice) ;

	public boolean Delete(Notice notice);

	public boolean Modify(Notice notice);
	
    public List Query(Notice notice);
    
    public List showMessage(Notice notice);
    
    public List Search(Notice notice);
}
