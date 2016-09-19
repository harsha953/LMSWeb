package com.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch>{

	public LibraryBranchDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(LibraryBranch be) throws SQLException {
		save("insert into tbl_library_branch(branchName, branchAddress) values(?,?)", 
				new Object[]{be.getBranchName(), be.getBranchAddress()});
		
	}

	@Override
	public void update(LibraryBranch be) throws SQLException {
		save("update tbl_library_branch set branchName=?, branchAddress=? where branchId=?", 
				new Object[]{be.getBranchName(), be.getBranchAddress(), be.getBranchId()});
		
	}

	@Override
	public void delete(LibraryBranch be) throws SQLException {
		save("delete from tbl_library_branch where branchId=?", new Object[]{be.getBranchId()});
		
	}

	@Override
	public LibraryBranch read(Integer[] pk) throws SQLException {
		return baseRead("select * from tbl_library_branch where branchId=?", pk);
	}

	@Override
	public List<LibraryBranch> readAll() throws SQLException {
		return baseReadAll("select * from tbl_library_branch", new Object[]{});
	}

	@Override
	public List<LibraryBranch> readResult(ResultSet rs) throws SQLException {
		List<LibraryBranch> list=new ArrayList<LibraryBranch>();
		while (rs.next()) {
			LibraryBranch lb=new LibraryBranch(rs.getString("branchName"), rs.getString("branchAddress"));
			lb.setBranchId(rs.getInt("branchId"));
			list.add(lb);
		}
		return list;
	}

}
