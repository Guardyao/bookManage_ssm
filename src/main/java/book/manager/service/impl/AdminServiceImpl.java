package book.manager.service.impl;

import book.manager.entity.Total;
import book.manager.entity.borrowDetails;
import book.manager.mapper.BookMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService
{
    @Resource
    BookMapper mapper;
    @Resource
    UserMapper mapper1;
    @Override
    public List<borrowDetails> getBorrowDetails()
    {
        return mapper.getBorrowDetails();
    }

    @Override
    public Total getTotal() {
        return new Total(mapper1.studentNumber(), mapper.bookNumber(),mapper.borrowNumber());
    }
}
