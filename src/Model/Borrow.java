package Model;
import java.util.Date;

public class Borrow extends Loan {
    public Borrow(int id, int subscriberId, int bookIsbn, Date borrowDate) {
        super(id, subscriberId, bookIsbn, borrowDate, null);
    }
}