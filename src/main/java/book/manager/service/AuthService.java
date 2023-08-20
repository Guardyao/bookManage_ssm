package book.manager.service;

import book.manager.entity.AuthUser;
import book.manager.entity.hasWrong;


import javax.servlet.http.HttpSession;

public interface AuthService {
    hasWrong register(String name, String sex, String grade, String password, String email, String code) ;
    AuthUser findUser(HttpSession session);
    hasWrong sendMessage(String email) throws Exception;

}
