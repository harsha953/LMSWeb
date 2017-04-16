package com.library.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.library.entity.BaseEntity;

public abstract class BaseDAO<BaseEntity> {
	protected Connection con;
	protected int pageNo;
	protected int pageSize;
	
	public BaseDAO(Connection con) {
		this.con = con;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public abstract void create(BaseEntity be) throws SQLException;
	public abstract void update(BaseEntity be) throws SQLException; 
	public abstract void delete(BaseEntity be) throws SQLException;
	public abstract BaseEntity read(Integer[] pk) throws SQLException;
	public abstract List<BaseEntity> readAll() throws SQLException;
	public abstract List<BaseEntity> readResult(ResultSet rs)throws SQLException;

	protected void save(String sql, Object[] values) throws SQLException{
		PreparedStatement stmt=con.prepareStatement(sql);
		int idx=1;
		for (Object object : values) {
			stmt.setObject(idx, object);
			idx++;
		}
		stmt.executeUpdate();
	}
	protected int saveGetCount(String sql, Object[] values) throws SQLException{
		ResultSet rs=makeStatement(sql, values);
		rs.next();
		return rs.getInt(1);
		
	}
	protected int saveAndGetId(String sql, Object[] values) throws SQLException{
		PreparedStatement stmt=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs=null;
		int idx=1;
		for (Object object : values) {
			stmt.setObject(idx, object);
			idx++;
		}
		stmt.executeUpdate();
		rs=stmt.getGeneratedKeys();
		rs.next();
		int key=rs.getInt(1);
		return key;
	}
	
	protected BaseEntity baseRead(String sql, Object[] values) throws SQLException{
		ResultSet rs = makeStatement(sql, values);
		List<BaseEntity> list=readResult(rs);
		if (list.isEmpty()) {
			return null;
		}else{
			return list.get(0);
		}
	}

	protected List<BaseEntity> baseReadAll(String sql, Object[] values) throws SQLException{
		ResultSet rs = makeStatement(sql, values);
		List<BaseEntity> list=readResult(rs);
		if (list.isEmpty()) {
			return null;
		}else{
			return list;
		}
	}
	protected List<BaseEntity> baseSelect(String sql, Object[] values) throws SQLException{
		pageNo=getPageNo();
		pageSize=getPageSize();
		if(pageNo>0){
			int index=(pageNo-1)*pageSize;
			sql+=" limit "+index+" , "+pageSize;
		}
		ResultSet rs = makeStatement(sql, values);
		List<BaseEntity> list=readResult(rs);
		if (list.isEmpty()) {
			return null;
		}else{
			return list;
		}
	}
	
	

	protected ResultSet makeStatement(String sql, Object[] values) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		stmt=con.prepareStatement(sql);
		int idx=1;
		for (Object i : values) {
			stmt.setObject(idx, i);
			idx++;
		}
		rs=stmt.executeQuery();
		return rs;	
	}

}
