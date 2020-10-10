package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Actor {
	 private int id;
	  private String firstName;
	  private String lastName;
	  private List<Film> films;
	
	  
	  
	  public Actor(int id, String firstName, String lastName, List<Film> films) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.films = films;
	}
	public Actor(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Actor() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Film> getFilms() {
		return films;
	}
	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append(" ");
		builder.append(firstName);
		builder.append(" ");
		builder.append(lastName);
		builder.append(" ");
		builder.append(films);
		return builder.toString();
	} 
	  
	  
	  
	  
	  

}
