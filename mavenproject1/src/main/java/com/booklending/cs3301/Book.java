package com.booklending.cs3301;

import java.util.Calendar;

class Book{
    String title;
    int id;
    int author;
    String category;
    int numOfCopies;
    static BookAvailability availability;
    static int numOfCopiesLent;
    static int totalBooksAvailable;
    Calendar issueDate;
    Calendar returnDate;

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
    public void setIssueDate (Date issueDate) {
        this.issueDate = issueDate;
    }
    public void setReturnDate (Date returnDate){
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
    Date getIssueDate(){
        return this.issueDate;
    }
    Date getReturnDate(){
        return this.returnDate;
    }
}