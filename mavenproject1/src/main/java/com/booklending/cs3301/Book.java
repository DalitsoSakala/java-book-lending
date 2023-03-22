package com.booklending.cs3301;

/**
 * @author Jonathan, Dalitso Sakala
 */
import 
import java.util.Calendar;

class Book{
    String title;
    int id;
    int author;
    String category;
    int numOfCopies;
    BookAvailability availability;
    static int numOfCopiesLent;
    static int totalBooksAvailable;
    Calendar issueDate;
    Calendar returnDate;
    private static int instanceCount=0;

    public Book(){
        instanceCount++;
        id=instanceCount;
    }
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setAuthor(int author){
        this.author = author;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setNumOfCopies(int numOfCopies){
        this.numOfCopies = numOfCopies;
    } 
    public void setIssueDate (Calendar issueDate) {
        this.issueDate = issueDate;
    }
    public void setReturnDate (Calendar returnDate){
        this.returnDate = returnDate;
    }

    String getTitle(){
        return this.title;
    }
    int getId(){
        return this.id;
    }
    int getAuthor(){
        return this.author;
    }
    String getCategory(){
        return this.category;
    }
    int getNumOfCopies(){
        return this.numOfCopies;
    }
    Calendar getIssueDate(){
        return this.issueDate;
    }
    Calendar getReturnDate(){
        return this.returnDate;
    }
}