package Model;
import java.util.Date;

public class Loan {

    private final int subscriberId;
    private int bookIsbn;
    private String borrowDate;
    private String returnDate;


    public Loan(int subscriberId, int bookIsbn, String borrowDate, String returnDate){
        this.subscriberId = subscriberId;
        this.bookIsbn = bookIsbn;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }



    public int getSubscriberId() {
        return subscriberId;
    }

    public int getBookIsbn() {
        return bookIsbn;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
