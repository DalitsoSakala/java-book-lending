package com.booklending.cs3301;

/**
 * @author Pumulo Mufalali, Dalitso Sakala
 */
import java.util.ArrayList;
import java.util.Calendar;

public class AppState {

    /**
     * Object that handles calls to update based on the state of an instance of
     * this class
     */
    IState windowManager;

    AppState(IState s) {
        windowManager = s;
    }
    // Data storage members
    ArrayList<Person> peopleList = new ArrayList<>();
    ArrayList<Book> bookList = new ArrayList<>();
    // end - Data storage members

    // Global context members
    private Person targetPerson;
    private Book targetBook;
    // end - global context members

    // Global context methods
    void resetTargets() {
        targetPerson = null;
        targetBook = null;
    }

    void setTargetBook(Book b) {
        targetBook = b;
    }

    void setTargetPerson(Person p) {
        targetPerson = p;
    }

    /**
     * Returns the list of target objects, `Book` and `Person`
     *
     * @return
     */
    Object[] getTargets() {
        return new Object[]{targetBook, targetPerson};
    }
    // end - global context methods

    // Allocation tab view data members
    private AllocationTabMode allocationTabMode;
    // end - Allocation tab view data members

    // Aggregate accessors
    public Book[] getBooksAvailable() {
        boolean add = true;
        ArrayList<Book> books = new ArrayList();
        Book returnBooks[];
        for (Object b : bookList.toArray()) {
            if (((Book) b).getAvailability() == BookAvailability.IN_SHELF) {
                books.add((Book) b);
            }
        }
        returnBooks = new Book[books.size()];
        return books.toArray(returnBooks);
    }

    public BookHolding[] getBooksHolders() {
        ArrayList<BookHolding> holdersList = new ArrayList<>();
        BookHolding returnHolding[];
        for (Person p : peopleList) {
            for (Book b : bookList) {
                if (b.getAvailability() != BookAvailability.LENT) {
                    continue;
                }
                if (b.getCurrentHolder() != p.getId()) {
                    continue;
                }
                holdersList.add(new BookHolding(p, b));
                break;

            }
        }
        returnHolding = new BookHolding[holdersList.size()];
        return holdersList.toArray(returnHolding);
    }

    public BookHolding[] getHoldersForBook(Book bk) {
        ArrayList<BookHolding> holderList = new ArrayList();
        BookHolding[] holdingsArr;
        for (BookHolding hd : getBooksHolders()) {
            System.out.print(bk+" <<<");
            if (!Book.isSameGroup(bk, hd.getBook())) {
                continue;
            }
            if (bk.getCurrentHolder() != hd.getPerson().getId()) {
                continue;
            }
            holderList.add(new BookHolding(hd.getPerson(), hd.getBook()));
        }
        holdingsArr = new BookHolding[holderList.size()];
        return holderList.toArray(holdingsArr);
    }

    public Person[] getFreePeople() {
        Person[] returnArray;
        ArrayList<Person> freeList = new ArrayList<>();
        boolean skip;
        for (Person psn : peopleList) {
            skip = false;
            for (Book bk : bookList) {
                if (bk.getAvailability() == BookAvailability.LENT) {
                    if (bk.getCurrentHolder() == psn.getId()) {
                        skip = true;
                        break;
                    }
                }
            }
            if (!skip) {
                freeList.add(psn);
            }
        }
        returnArray = new Person[freeList.size()];
        return freeList.toArray(returnArray);
    }

    public Book[] getBooksLent() {
        boolean add = true;
        ArrayList<Book> books = new ArrayList();
        Book returnBooks[];
        for (Book b : bookList) {
            if (b.getAvailability() == BookAvailability.LENT) {
                books.add(b);
            }
        }
        returnBooks = new Book[books.size()];
        return books.toArray(returnBooks);
    }
    // end - Aggregate accessors

    public AllocationTabMode getAllocationTabMode() {
        return allocationTabMode;
    }

    public void setAllocationTabMode(AllocationTabMode allocationTabMode) {
        this.allocationTabMode = allocationTabMode;
        updateListener();
    }

    // Actions according to tabs
    void addUser(Person p) {
        peopleList.add(p);
        updateListener();
    }

    void addBook(Book b) {
        bookList.add(b);
        updateListener();

    }

    void assignBook() {
        targetBook.setAvailability(BookAvailability.LENT, targetPerson.getId());
        targetBook.setIssueDate(Calendar.getInstance());
        targetBook.setReturnDate(null);
        targetBook.setCurrentHolder(targetPerson.getId());
        resetTargets();
        updateListener();
    }

    void recoverBook() {
        Book bk=targetBook;
        bk.setAvailability(BookAvailability.IN_SHELF, -1);
        bk.setIssueDate(null);
        bk.setReturnDate(null);
        bk.setCurrentHolder(-1);
        updateListener();
    }
    // end - Actions according to tabs

    // JFrame update request
    void updateListener() {
        windowManager.updateState(this);
    }
}

interface IState {

    void updateState(AppState s);
}

enum AllocationTabMode {
    ASSIGNMENT,
    RECOVERY
}

class BookHolding {

    private final Person person;
    private final Book book;

    BookHolding(Person p, Book b) {
        person = p;
        book = b;
    }

    @Override
    public String toString() {
        return person.getName() + " (" + book.getTitle() + ")";
    }

    public Person getPerson() {
        return person;
    }

    public Book getBook() {
        return book;
    }
}
