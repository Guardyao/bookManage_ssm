package book.manager.controller.page;

import book.manager.service.AdminService;
import book.manager.service.AuthService;
import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/admin")
public class AdminPageController
{

    @Resource
    AuthService service;
    @Resource
    BookService service1;
    @Resource
    AdminService service2;

    @RequestMapping("/index")
    public String index(HttpSession session, Model model)
        {
            model.addAttribute("user",service.findUser(session));
            model.addAttribute("borrowDetailList",service2.getBorrowDetails());
            model.addAttribute("total",service2.getTotal());
            return "/admin/index";
        }
    @RequestMapping("/book")
    public String book(HttpSession session, Model model)
    {
        model.addAttribute("user",service.findUser(session));
        model.addAttribute("bookList",service1.getAllBook());
        return "/admin/book";
    }
    @RequestMapping("/add-book")
    public String addBook(HttpSession session, Model model)
    {
        model.addAttribute("user",service.findUser(session));
        return "/admin/add-book";
    }
}
