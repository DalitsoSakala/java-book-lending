package com.booklending.cs3301;

/**
 * @author Jonathan Phiri, Dalitso Sakala
 */
import java.util.Calendar;

class Book {
    String title;
    final int id;
    final String author;
    String category;
    BookAvailability availability;
    Calendar issueDate;
    Calendar returnDate;
    private static int instanceCount = 0;

    public Book(String title, String author, String category) {
        instanceCount++;
        id = instanceCount;
        this.author = author;
        this.category = category;
        availability = BookAvailability.IN_SHELF;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIssueDate(Calendar issueDate) {
        this.issueDate = issueDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    String getTitle() {
        return this.title;
    }

    int getId() {
        return this.id;
    }

    String getAuthor() {
        return this.author;
    }

    String getCategory() {
        return this.category;
    }

    Calendar getIssueDate() {
        return this.issueDate;
    }

    Calendar getReturnDate() {
        return this.returnDate;
    }
}