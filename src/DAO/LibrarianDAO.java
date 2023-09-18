package DAO;

import Model.Librarian;

import java.util.Optional;

public interface LibrarianDAO {
    Librarian authenticateLibrarian(String email, String code);
    boolean doesEmailExist(String email);
    boolean doesCodeExist(String code);
    boolean isValidEmail(String email);
    boolean addLibrarian(Librarian librarian);
    Optional<Librarian> getLibrarianByEmail(String email);
}
