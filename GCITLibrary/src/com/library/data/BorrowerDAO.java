package com.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.BookLoans;
import com.library.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> {

	public BorrowerDAO(Connection con) {
		super(con);
	}

	@Override
	public void create(Borrower be) throws SQLException {
		save("insert into tbl_borrower(name, address, phone) values(?,?,?)", new Object[] { be.getName(), be.getAddress(), be.getPhone() });

	}

	@Override
	public void update(Borrower be) throws SQLException {
		save("update tbl_borrower set name=?, address=?, phone=? where cardNo=?",
				new Object[] { be.getName(), be.getAddress(), be.getPhone(), be.getCardNo() });

	}

	@Override
	public Borrower read(Integer[] pk) throws SQLException {
		return baseRead("select * from tbl_borrower where cardNo=?", pk);
	}

	@Override
	public void delete(Borrower be) throws SQLException {
		save("delete from tbl_borrower where cardNo=?", new Object[] { be.getCardNo() });

	}

	@Override
	public List<Borrower> readAll() throws SQLException {
		return baseReadAll("select * from tbl_borrower", new Object[] {});
	}

	@Override
	public List<Borrower> readResult(ResultSet rs) throws SQLException {
		List<Borrower> list = new ArrayList<Borrower>();
		while (rs.next()) {
			Borrower b = new Borrower(rs.getString("name"), rs.getString("address"), rs.getString("phone"));
			b.setCardNo(rs.getInt("cardNo"));
			list.add(b);
		}
		return list;
	}

	

}
