package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";
	

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
//			System.out.println("");
			e.printStackTrace();
		}
	}
	
	
  @Override
  public Film findFilmById(int filmId) {
	  Film film = null;
	  
	  try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
//		String sqltxt = "SELECT * FROM film WHERE film.id = ?";
		String sqltxt = "SELECT * FROM film JOIN language ON language.id = film.language_id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			film = new Film();
			
			film.setFilmId(filmId);
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setLanguage(findLanguageById(rs.getInt("language_id")).getName());
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			
			
			List<Actor> actors = findActorsByFilmId(filmId);
			film.setActors(actors);
		}
	  rs.close();
	  stmt.close();
	  conn.close();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
    return film;
	 
  }

@Override
public Actor findActorById(int actorId) {
	Actor actor = null;
	
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sqltxt = "SELECT * FROM actor WHERE  id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return actor;
}

@Override
public List<Actor> findActorsByFilmId(int filmId) {
	List<Actor> actors = new ArrayList<Actor>();
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sqltxt = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "WHERE film_actor.film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Actor actor = new Actor();
			actor.setId(rs.getInt("id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			
			
			actors.add(actor);
		}
	rs.close();
	stmt.close();
	conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return actors;
}


@Override
public List<Film> findFilmBySearch(String keyword) {
	Film film = null;
	List<Film> films = new ArrayList<>();
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sqltxt = "SELECT * FROM film WHERE description "
				+ "LIKE ? OR title LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setString(1, "%" + keyword + "%");
		stmt.setString(2, "%" + keyword + "%");
		ResultSet rs = stmt.executeQuery();
	
	while(rs.next()) {
		film = new Film ();
		int filmId = rs.getInt("id");
		film.setFilmId(rs.getInt("id"));
		film.setTitle(rs.getString("title"));
		film.setDescription(rs.getString("description"));
		film.setReleaseYear(rs.getInt("release_year"));
		film.setLanguage(findLanguageById(rs.getInt("language_id")).getName());
		film.setRentalDuration(rs.getInt("rental_duration"));
		film.setRentalRate(rs.getDouble("rental_rate"));
		film.setLength(rs.getInt("length"));
		film.setReplacementCost(rs.getDouble("replacement_cost"));
		film.setRating(rs.getString("rating"));
		film.setSpecialFeatures(rs.getString("special_features"));
		
		List<Actor> actors = findActorsByFilmId(filmId);
		film.setActors(actors);
		films.add(film);
	}
	rs.close();
	stmt.close();
	conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return films;
}

//public String getLanguage(String language) {
//	// TODO Auto-generated method stub
//	return null;
//}

@Override
public Language findLanguageById(int languageId) {
	Language lang = null;
	
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sqltxt = "SELECT name FROM language WHERE "
				+ "language.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, languageId);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			String name = rs.getString("name");
			lang = new Language(name);
		}
	rs.close();
	stmt.close();
	conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return lang;
}












}
