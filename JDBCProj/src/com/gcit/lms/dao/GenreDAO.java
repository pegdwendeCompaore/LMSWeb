package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Genre;

@SuppressWarnings("unchecked")
public class GenreDAO extends BaseDAO{

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addBranch(Genre genre) throws SQLException
	{
		save("insert into tbl_genre (genre_name) values (?)", new Object []{genre.getGenreName()});
	}
	public Integer addGenreWithId(Genre genre) throws SQLException
	{
		return saveWithID("insert into tbl_genre (genre_name) values (?)", new Object []{genre.getGenreName()});
	}
	public void updateGenre(Genre genre) throws SQLException {
		save("update tbl_genre set genre_name = ? where genre_Id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws SQLException {
		save("delete from tbl_genre where genreId = ?", new Object[] { genre.getGenreId() });
	}

	public List<Genre> readAllGenre() throws SQLException {
		return read("select * from tbl_genre", null);
	}

	
	@Override
	public List<Genre> extractData(ResultSet rs) {
		List<Genre> genre = new ArrayList<Genre>();
		BookDAO bdao = new BookDAO(conn);
		try {
			while (rs.next()) {
				Genre g = new Genre();
				g.setGenreId(rs.getInt("genre_Id"));
				g.setGenreName(rs.getString("genre_name"));
				g.setBooks(bdao.readFirstLevel(
						"select * from tbl_book where bookId IN (select bookId from tbl_book_genre where genre_Id = ?))",
						new Object[] { g.getGenreId()}));
				genre.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genre;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) {
		List<Genre> genre = new ArrayList<Genre>();
		try {
			while (rs.next()) {
				Genre g = new Genre();
				g.setGenreId(rs.getInt("genre_Id"));
				g.setGenreName(rs.getString("genre_name"));
				genre.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genre;
	}
	
	

}
