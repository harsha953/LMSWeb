package com.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{

	public PublisherDAO(Connection con) {
		super(con);
	}

	@Override
	public void create(Publisher be) throws SQLException {
		save("insert into tbl_publisher(publisherName, publisherAddress, publisherPhone) values(?,?,?)", 
				new Object[]{be.getPublisherName(), be.getPublisherAddress(), be.getPublisherPhone()});
	}

	@Override
	public void update(Publisher be) throws SQLException {
		save("update tbl_publisher set publisherName=?, publisherAddress=?, publisherPhone=? where publisherId=?", 
				new Object[]{be.getPublisherName(), be.getPublisherAddress(), be.getPublisherPhone(), be.getPublisherId()});
	}

	@Override
	public Publisher read(Integer[] pk) throws SQLException {
		return baseRead("select * from tbl_publisher where publisherId=?", pk);
	}

	@Override
	public void delete(Publisher be) throws SQLException {
		save("delete from tbl_publisher where publisherId=?", new Object[]{be.getPublisherId()});
		
	}

	@Override
	public List<Publisher> readAll() throws SQLException {
		return baseReadAll("select * from tbl_publisher", new Object[]{});
	}

	@Override
	public List<Publisher> readResult(ResultSet rs) throws SQLException {
		List<Publisher> list=new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher p=new Publisher(rs.getString("publisherName"), rs.getString("publisherAddress"), rs.getString("publisherPhone"));
			p.setPublisherId(rs.getInt("publisherId"));
			list.add(p);
		}

		return list;
	}
}
