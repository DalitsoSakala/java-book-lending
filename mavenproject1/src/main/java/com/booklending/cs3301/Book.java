package com.booklending.cs3301;

/**
 * @author Jonathan Phiri, Dalitso Sakala
 */
import java.util.Calendar;
import java.util.ArrayList;

class Book {

    private final String title;
    private final int id;
    private final String author;
    private final String category;
    private BookAvailability availability;
    private Calendar issueDate;
    private Calendar returnDate;
    private int currentHolder;
    private final String bookGroup;


    private static int instanceCount = 0;

    public Book(String title, String author, String category) {
        instanceCount++;
        id = instanceCount;
        this.author = author.trim().replaceAll("\\s", " ");
        this.category = category;
        this.title = title.trim().replaceAll("\\s", " ");;
        availability = BookAvailability.IN_SHELF;
        bookGroup=(this.title+this.author+category).toLowerCase().replaceAll("\\s", "-");
    }

    public static boolean isSameGroup(Book b1, Book b2) {
        System.out.println(b1+" "+b2+" end>>");
        return b1.bookGroup.equals(b2.bookGroup);
    }

    public static Book[] filterByGroup(Book[] books) {
        ArrayList<Integer> skip = new ArrayList();
        ArrayList<Book> bookList=new ArrayList();
        Book[] returnBooks;
        for (int i = 0; i < books.length;) {
            Book bk = books[i];
            if(!skip.contains(i))
                bookList.add(bk);
            for (int x = i+1; x < books.length; x++) {
                if (skip.contains(x)) {
                    continue;
                }
                if (Book.isSameGroup(books[x], bk)) {
                    skip.add(x);
                }
            }
            i++;
        }
        returnBooks=new Book[bookList.size()];
        return bookList.toArray(returnBooks);
    }

    public BookAvailability getAvailability() {
        return availability;
    }

    @Override
    public String toString() {
        return this.title + " ~ By " + this.author;
    }

    public void setAvailability(BookAvailability availability, int holder) {
        switch (availability) {
            case LENT:
                currentHolder = holder;
                break;
            case IN_SHELF:
                currentHolder = -1;
        }
        this.availability = availability;
    }
    public String getBookGroup() {
        return bookGroup;
    }

    public int getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(int personId) {
        currentHolder=personId;
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


enum BookAvailability{
    LENT,
    IN_SHELF

}
