package Model;

public class LibraryStatistics {
    private int totalBooksInLibrary;
    private int totalBooksBorrowed;
    private int totalBooksReturned;
    private int lostBooks;

    public LibraryStatistics(int totalBooksInLibrary, int totalBooksBorrowed, int totalBooksReturned, int lostBooks) {
        this.totalBooksInLibrary = totalBooksInLibrary;
        this.totalBooksBorrowed = totalBooksBorrowed;
        this.totalBooksReturned = totalBooksReturned;
        this.lostBooks = lostBooks;
    }

    // Getters and setters for the fields
    public int getTotalBooksInLibrary() {
        return totalBooksInLibrary;
    }

    public void setTotalBooksInLibrary(int totalBooksInLibrary) {
        this.totalBooksInLibrary = totalBooksInLibrary;
    }

    public int getTotalBooksBorrowed() {
        return totalBooksBorrowed;
    }

    public void setTotalBooksBorrowed(int totalBooksBorrowed) {
        this.totalBooksBorrowed = totalBooksBorrowed;
    }

    public int getTotalBooksReturned() {
        return totalBooksReturned;
    }

    public void setTotalBooksReturned(int totalBooksReturned) {
        this.totalBooksReturned = totalBooksReturned;
    }

    public int getLostBooks() {
        return lostBooks;
    }

    public void setLostBooks(int lostBooks) {
        this.lostBooks = lostBooks;
    }
}
