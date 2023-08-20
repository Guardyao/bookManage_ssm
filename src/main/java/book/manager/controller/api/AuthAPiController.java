package book.manager.controller.api;

import book.manager.entity.hasWrong;
import book.manager.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/auth")
public class AuthAPiController {

        @Resource
        AuthService service;
        @RequestMapping(value = "/verifyCode")
        public hasWrong VerifyCode(@RequestParam("email") String email) throws Exception {
                return service.sendMessage(email);
        }
        @RequestMapping(value = "/register",method = RequestMethod.POST)
        public String register(@RequestParam("username") String name,
                                 @RequestParam("sex") String sex,
                                 @RequestParam("grade") String grade,
                                 @RequestParam("password") String password,
                                 @RequestParam("email") String email,
                                 @RequestParam("code") String code) throws Exception {
                service.register(name, sex,grade,password, email,code);
                return "redirect:/login";//重定向
        }

}
