package book.manager.service;

import book.manager.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBook();
    List<Book> getAllBookWithoutBorrow();
    List<Book> getBorrowedBookById(int uid);
    void deleteBook(int bid);
    void addBook(String title, String author, double price);
    void borrowBook(int bid, int uid);
    void returnBook(int bid);
}
