import java.util.Calendar;

class Book{
    String title;
    int id;
    int author;
    String category;
    static int num_of_copies;
    static BookAvailability availability;
    static int num_of_copies_lent;
    static int total_books_available;
    Calendar issue_date;
    Calendar return_date;
}   