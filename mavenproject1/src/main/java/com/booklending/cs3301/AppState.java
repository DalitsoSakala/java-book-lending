package com.booklending.cs3301;

/**
 * @author Pumulo Mufalali
 */
import java.util.ArrayList;

public class AppState {
    // Data members
    ArrayList<Person> peopleList=new ArrayList<>(); 
    ArrayList<Book> bookList=new ArrayList<>();
    ArrayList<String> categories=new ArrayList<>();
    // end - Data members

    // Aggregate accessors
    public Book[] getBooksAvailable(){
        Book books[]= {};
        return books;
    }
    public Book[] getBooksLent(){
        Book books[]= {};
        return books;
    }
    // end - Aggregate accessors


    // Actions according to tabs
    void addUser(Person p){

    }
    
    void addBook(Book b){

    }
    void assignBook(Book b, Person p){

    }
    // end - Actions according to tabs
}
