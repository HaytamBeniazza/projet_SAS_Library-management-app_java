package DAO;

import java.util.Date;

public interface ReturnDAO {
    void returnBook(int userId, int bookIsbn, Date returnDate);
}

