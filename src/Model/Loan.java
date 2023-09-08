package Model;
import java.util.Date;

public abstract class Loan {

    private final int id;
    private final int subscriberId;
    private int bookIsbn;
    private Date borrowDate;
    private Date returnDate;


    public Loan(int id, int subscriberId, int bookIsbn, Date borrowDate, Date returnDate){
        this.id = id;
        this.subscriberId = subscriberId;
        this.bookIsbn = bookIsbn;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public int getBookIsbn() {
        return bookIsbn;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
