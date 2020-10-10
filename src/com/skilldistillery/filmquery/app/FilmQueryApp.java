package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//    app.test();
    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
    
//    Actor actor = db.findActorById(1);
//    System.out.println(actor);
    
//    List<Actor>  actors = db.findActorsByFilmId(1); 
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  List<Film> films = new ArrayList<>();
	  boolean menu = true;
	  
	  Film film;
	  System.out.println("Introducing: The Film Query Database");
	  while(menu) {
		  System.out.println("\n===***===***===***===***===***===***===***===***===***===***");
		  System.out.println("\n===***===***===***FILM QUERY MAIN MENU***===***===***===****");
		  System.out.println("\nPlease Enter 1: To Look Up A Film By Its ID Number.");
		  System.out.println("Please Enter 2: To Look Up A Film By A Search Keyword.");
		  System.out.println("Please Enter 3: Exit The Application.");
		  System.out.println("Please Enter Your Selection Now 1-3: \n>>>>>>>>>>>>>>>>>");
		  int select = 0;
		  try {
			  select = input.nextInt();
			  
		  } catch (InputMismatchException e) {
			  String badInput = input.next();
			  System.out.println("You entered an Invalid input of: "+ badInput+  ". \nPlease enter 1-3.");
//			  select = input.nextInt();
			  continue;
//			  break;
		  }

		  switch (select) {
		  case 1:
			  System.out.println("Please enter the film id number: ");
			  film = db.findFilmById(input.nextInt());
			  if (film == null) {
				  System.out.println("Oops! We could not find a matching film with that ID number.");
				  System.out.println("Please try again!");
			  }else {
				  System.out.println(film.displayFilm());
//				  System.out.println("\nPlease ");
				  
			  }
			  break;
		  case 2:
			  System.out.println("Please enter keyword to search: ");
			  films = db.findFilmBySearch(input.next());
			  if(films.isEmpty()) {
				  System.out.println("Oops! We could not find a matching film with that keyword search.");
				  System.out.println("Please try again!");
			  }else {
				  for (Film film2 : films) {
					  System.out.println(film2.displayFilm());
				}
			  }
			  break;
		  case 3: 
			  System.out.println("======>GOODBYE! HAVE A NICE DAY!<=======");
			  menu = false;
			  break;
		  }
	  }
  }

}
