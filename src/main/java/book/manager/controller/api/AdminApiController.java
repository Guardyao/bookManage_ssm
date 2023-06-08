package book.manager.controller.api;

import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/admin")
public class AdminApiController
{
    @Resource
    BookService service;
    @RequestMapping(value = "/deleteBook",method = RequestMethod.GET)
    public String deleteBook(@RequestParam("id") int bid)
    {
        service.deleteBook(bid);
        return "redirect:/page/admin/book";
    }
    @RequestMapping(value = "/addBook",method = RequestMethod.POST)
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("author") String author,
                          @RequestParam("price") double price)
    {
        service.addBook(title,author,price);
        return "redirect:/page/admin/book";
    }
}
