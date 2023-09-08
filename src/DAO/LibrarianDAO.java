package DAO;

import Model.Librarian;

import java.util.Optional;

public interface LibrarianDAO {
    Optional<Librarian> authenticateLibrarian(String email, String code);
    boolean doesEmailExist(String email);
    boolean doesCodeExist(String code);
    boolean isValidEmail(String email);
    void addLibrarian(Librarian librarian);
    Optional<Librarian> getLibrarianByEmail(String email);
}
