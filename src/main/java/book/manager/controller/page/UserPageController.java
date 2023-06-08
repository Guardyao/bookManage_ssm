package book.manager.controller.page;

import book.manager.entity.AuthUser;
import book.manager.service.AuthService;
import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/user")
public class UserPageController
{
    @Resource
    AuthService service;
    @Resource
    BookService service1;
    @RequestMapping("/index")
    public String index(HttpSession session, Model model)
    {
        model.addAttribute("user",service.findUser(session));
        model.addAttribute("bookList",service1.getAllBookWithoutBorrow());
        return "/user/index";
    }
    @RequestMapping("/book")
    public String book(HttpSession session, Model model)
    {
        AuthUser user = service.findUser(session);
        model.addAttribute("user",user);
        model.addAttribute("bookList",service1.getBorrowedBookById(user.getId()));
        return "/user/book";
    }
}
