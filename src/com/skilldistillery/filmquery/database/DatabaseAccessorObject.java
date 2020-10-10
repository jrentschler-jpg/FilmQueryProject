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
//			Language language = new Language();
//			filmId = rs.getInt("id");
//			title = rs.getString("title");
//			String description = rs.getString("description");
//			Integer releaseYear = rs.getInt("release_year");
//			String Language = rs.getString("Language");
////			film.setLangId(rs.getInt("language_id"));
//			int rentalDuration = rs.getInt("rental_duration");
//			double rentalRate = rs.getDouble("rental_rate");
//			int length = rs.getInt("length");
//			double replacementCost = rs.getDouble("replacement_cost");
//			String rating = rs.getString("rating");
//			String specialFeatures = rs.getString("special_features");

			

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
//			film = new Film(filmId, title, description, releaseYear, Language, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, actors);
//			actors.add(film);
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
//			actor.setFilms(findFilmsByActorId(actorId));
			
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
//			int id = rs.getInt("id");
//			String firstName = rs.getString("first_name");
//			String lastName = rs.getString("last_name");
//			Actor actor = new Actor(id, firstName, lastName);
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
//@Override
//public Language getLanguage(int langId) {
//	
//}

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
		
//		String title = rs.getString("title");
		film.setTitle(rs.getString("title"));
		
//		String description = rs.getString("description");
		film.setDescription(rs.getString("description"));
		
//		Integer releaseYear = rs.getInt("release_year");
		film.setReleaseYear(rs.getInt("release_year"));
		
//		String language = rs.getString("Language");
		film.setLanguage(findLanguageById(rs.getInt("language_id")).getName());
		
//		int rentalDuration = rs.getInt("rental_duration");
		film.setRentalDuration(rs.getInt("rental_duration"));
		
//		double rentalRate = rs.getDouble("rental_rate");
		film.setRentalRate(rs.getDouble("rental_rate"));
		
//		int length = rs.getInt("length");
		film.setLength(rs.getInt("length"));
		
//		double replacementCost = rs.getDouble("replacement_cost");
		film.setReplacementCost(rs.getDouble("replacement_cost"));
		
//		String rating = rs.getString("rating");
		film.setRating(rs.getString("rating"));
		
//		String specialFeatures = rs.getString("special_features");
		film.setSpecialFeatures(rs.getString("special_features"));
		
	
		List<Actor> actors = findActorsByFilmId(filmId);
		film.setActors(actors);
//		film = new Film(filmId, title, description, releaseYear, language, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, actors);
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
