package com.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.Genre;

public class GenreDAO extends BaseDAO<Genre>{

	public GenreDAO(Connection con) {
		super(con);
	}

	@Override
	public void create(Genre be) throws SQLException {
		save("insert into tbl_genre(genre_name) values(?)", new Object[]{be.getGenreName()});
		
	}

	@Override
	public void update(Genre be) throws SQLException {
		save("update tbl_genre set genre_name=? where genre_id=?", 
				new Object[]{be.getGenreName(), be.getGenreId()});
		
	}

	@Override
	public Genre read(Integer[] pk) throws SQLException {
		return baseRead("select * from tbl_genre where genre_id=?", pk);
	}

	@Override
	public void delete(Genre be) throws SQLException {
		save("delete from tbl_genre where genre_id=?", new Object[]{be.getGenreId()});
		
	}

	@Override
	public List<Genre> readAll() throws SQLException {
		return baseReadAll("select * from tbl_genre", new Object[]{});
	}

	@Override
	public List<Genre> readResult(ResultSet rs) throws SQLException {
		List<Genre> list=new ArrayList<Genre>();
		while (rs.next()) {
			Genre g=new Genre(rs.getString("genreName"));
			g.setGenreId(rs.getInt("genreId"));
			list.add(g);
		}
		return list;
	}

}
