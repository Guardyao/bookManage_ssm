package book.manager.mapper;

import book.manager.entity.Book;
import book.manager.entity.Borrow;
import book.manager.entity.Total;
import book.manager.entity.borrowDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    @Select("select * from book")
    List<Book> getAllBook();

    @Delete("delete from book where bid = #{bid}")
    void deleteBook(int bid);

    @Insert("insert into book(title,author,price) value(#{title},#{author},#{price})")
    void addBook(@Param("title") String title,@Param("author") String author,@Param("price") double price);

    @Insert("insert into borrow(bid,sid,time) value(#{bid},#{sid},NOW())")
    void borrowBook(@Param("bid") int bid, @Param("sid") int sid);

    @Select("select * from borrow")
    List<Borrow> getBorrowList();

    @Select("select * from borrow where sid = #{sid}")
    List<Borrow> getBorrowListBySid(int sid);

    @Select("select * from book where bid = #{bid}")
    Book getBookByBid(int bid);

    @Delete("delete from borrow where bid = #{bid}")
    void deleteBorrow(int bid);

    @Results({
            @Result(id = true,column = "id", property = "id"),
            @Result(column = "sid", property = "sid"),
            @Result(column = "name", property = "user_name"),
            @Result(column = "bid", property = "bid"),
            @Result(column = "title",property = "book_title"),
            @Result( column = "time",property = "time")
    })
    @Select("select * from borrow left join book on borrow.bid = book.bid left join student on borrow.sid = student.sid")
    List<borrowDetails> getBorrowDetails();

    @Select("SELECT COUNT(*) FROM book")
    int bookNumber();

    @Select("SELECT COUNT(*) FROM borrow")
    int borrowNumber();

}
