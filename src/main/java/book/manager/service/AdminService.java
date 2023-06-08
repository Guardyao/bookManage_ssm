package book.manager.service;

import book.manager.entity.Total;
import book.manager.entity.borrowDetails;

import java.util.List;


public interface AdminService
{
    List<borrowDetails> getBorrowDetails();
    Total getTotal();
}
