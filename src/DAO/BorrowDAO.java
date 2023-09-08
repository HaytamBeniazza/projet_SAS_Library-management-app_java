package DAO;

import java.util.Date;

public interface BorrowDAO {
    void borrowBook(int userId, int bookIsbn, Date borrowDate);
}

