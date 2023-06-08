package book.manager.service.impl;

import book.manager.entity.Book;
import book.manager.entity.Borrow;
import book.manager.mapper.BookMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService
{
    @Resource
    BookMapper mapper;
    @Resource
    UserMapper mapper1;
    @Override
    public List<Book> getAllBook()
    {
        return mapper.getAllBook();
    }

    @Override
    public List<Book> getAllBookWithoutBorrow()
    {
        List<Book> books = mapper.getAllBook();
        List<Integer> borrows = mapper.getBorrowList()
                .stream()
//                .map(borrow -> borrow.getBid())
                .map(Borrow::getBid)
                .collect(Collectors.toList());
        return books
                .stream()
                .filter(book -> !borrows.contains(book.getBid())).
                collect(Collectors.toList());
    }

    @Override
    public void returnBook(int bid) {
        mapper.deleteBorrow(bid);
    }

    @Override
    public List<Book> getBorrowedBookById(int uid) {
        Integer sid = mapper1.getSidByUid(uid);
        if (sid == null) return Collections.emptyList();
        return mapper.getBorrowListBySid(sid)
                .stream()
                .map(borrow -> mapper.getBookByBid(borrow.getBid()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(int bid)
    {
        mapper.deleteBook(bid);
    }

    @Override
    public void borrowBook(int bid, int uid)
    {
        Integer sid = mapper1.getSidByUid(uid);
        if (sid == null) return;
        mapper.borrowBook(bid,sid);
    }

    @Override
    public void addBook(String title, String author, double price)
    {
        mapper.addBook(title, author, price);
    }
}
