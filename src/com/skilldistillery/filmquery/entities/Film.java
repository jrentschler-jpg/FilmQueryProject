package com.skilldistillery.filmquery.entities;

import java.util.List;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class Film {
	private int filmId;
	private String title;
	private String description;
	private int releaseYear;
	private int langId;
	private String language;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	
	
	
	public Film(int filmId, String title, String description, int releaseYear,int langId, String language, int rentalDuration,
		double rentalRate, int length, double replacementCost, String rating, String specialFeatures, List<Actor> actors) {
	super();
	this.filmId = filmId;
	this.title = title;
	this.description = description;
	this.releaseYear = releaseYear;
	this.language = language;
	this.rentalDuration = rentalDuration;
	this.rentalRate = rentalRate;
	this.length = length;
	this.replacementCost = replacementCost;
	this.rating = rating;
	this.specialFeatures = specialFeatures;
	this.actors = actors;
}
	public Film(int filmId, String title, String description, int releaseYear, String language, int rentalDuration,
			double rentalRate, int length, double replacementCost, String rating, String specialFeatures) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		
	}
	public Film () {
		super();
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public int getLangId() {
		return langId;
	}
	public void setLangId(int langId) {
		this.langId = langId;
	}
	public int getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public double getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getReplacementCost() {
		return replacementCost;
	}
	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	
	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
public String displayFilm() {
//	DatabaseAccessorObject db = new DatabaseAccessorObject();
	StringBuilder builder = new StringBuilder();
	builder.append("\nTitle: ");
	builder.append(title);
	builder.append("\nRelease Year: ");
	builder.append(releaseYear);
	builder.append("\nRating: ");
	builder.append(rating);
	builder.append("\nDescription: ");
	builder.append(description);
	builder.append("\nLanguage: ");
	builder.append(language);
	builder.append("\nActor's List: ");
	builder.append(actors);
	for (int i = 0; i < actors.size(); i++) {
		builder.append(actors.get(i));
		if(i < actors.size() ) {
			builder.append(", ");
		}
		
	}
	
	return builder.toString();
}
	@Override
	public String toString() {
//		DatabaseAccessorObject db = new DatabaseAccessorObject();
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("\nFilm ID:");
		builder.append(filmId);
		builder.append("\nTitle: ");
		builder.append(title);
		builder.append("\nDescription: ");
		builder.append(description);
		builder.append("\nRelease Year: ");
		builder.append(releaseYear);
		builder.append("\nLanguage: ");
		builder.append(language);
		builder.append("\nRental Duration:");
		builder.append(rentalDuration);
		builder.append("\nRental Rate:");
		builder.append(rentalRate);
		builder.append("\nLength: ");
		builder.append(length);
		builder.append("\nReplacement Cost: ");
		builder.append(replacementCost);
		builder.append("\nRating: ");
		builder.append(rating);
		builder.append("\nSpecial Features: ");
		builder.append(specialFeatures);
		builder.append("\nActor's List: ");
		builder.append(actors);
		
		for (int i = 0; i < actors.size(); i++) {
			builder.append(actors.get(i));
			if(i < actors.size() -1) {
				builder.append(", ");
			}
			
		}
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + filmId;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + length;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + rentalDuration;
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(replacementCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialFeatures == null) ? 0 : specialFeatures.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (filmId != other.filmId)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (length != other.length)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (rentalDuration != other.rentalDuration)
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(replacementCost) != Double.doubleToLongBits(other.replacementCost))
			return false;
		if (specialFeatures == null) {
			if (other.specialFeatures != null)
				return false;
		} else if (!specialFeatures.equals(other.specialFeatures))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	

}
