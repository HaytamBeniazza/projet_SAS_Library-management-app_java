// Return.java
package Model;

import java.util.Date;

public class Return extends Loan {
    public Return(int id, int subscriberId, int bookIsbn, Date returnDate) {
        super(id, subscriberId, bookIsbn, null, returnDate);
    }
}
