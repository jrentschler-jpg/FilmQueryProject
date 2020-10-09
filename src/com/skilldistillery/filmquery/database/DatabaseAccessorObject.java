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

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";
	
	public DatabaseAccessorObject() throws ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver");
	}
	
	
  @Override
  public Film findFilmById(int filmId) {
	  Film film = null;
	  
	  try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sqltxt = "SELECT * FROM film WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int filId = rs.getInt("id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			Integer releaseYear = rs.getInt("release_year");
			int langId = rs.getInt("language_id");
			int rentalDuration = rs.getInt("rental_duration");
			double rentalRate = rs.getDouble("rental_rate");
			int length = rs.getInt("length");
			double replacementCost = rs.getDouble("replacement_cost");
			String rating = rs.getString("rating");
			String specialFeatures = rs.getString("special_features");
			
			List<Actor> actors = findActorsByFilmId(filmId);
			film = new Film(filmId, title, description, releaseYear, langId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures);
//			actors.add(actors);
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
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			Actor actor = new Actor(id, firstName, lastName,films);
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

}
