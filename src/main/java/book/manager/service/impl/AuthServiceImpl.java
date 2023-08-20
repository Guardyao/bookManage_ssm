package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.entity.hasWrong;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService
{

    @Resource
    UserMapper mapper;

    @Override
    public AuthUser findUser(HttpSession session)
    {
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null)
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = mapper.getUserByName(authentication.getName());
            session.setAttribute("user",user);
        }
        return user;
    }

    @Transactional
    @Override
    public hasWrong sendMessage(String email) throws MessagingException {
        String subject = "欢迎访问我的世界";
        int cs = new Random().nextInt(899999) + 100000;
        String message = "您的注册验证码是："+ cs +",有效期为三分钟，如果不是本人操作，请忽略";
        System.out.println(mapper.getEmail(email));
        if (mapper.getEmail(email) != null) return new hasWrong(400,"账户已存在");
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.port", "587");

        // 创建会话
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2265723996@qq.com", "nbkzupprnoymdhji");
            }
        });

        // 创建邮件消息
        Message emailMessage = new MimeMessage(session);
        emailMessage.setFrom(new InternetAddress("2265723996@qq.com"));
        emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        emailMessage.setSubject(subject);
        emailMessage.setText(message);

        // 发送邮件
        Transport.send(emailMessage);
        mapper.verify(email,cs+"");
        return new hasWrong(400,"邮件发送成功");
    }

    @Transactional
    @Override
    public hasWrong register(String name, String sex, String grade, String password, String email, String code)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AuthUser user = new AuthUser(0,name,encoder.encode(password),"user");
        if (!mapper.getCode(email).equals(code))
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            mapper.deleteCode(email);
            return new hasWrong(404,"验证码有误");
        }

        if (mapper.registerUser(user) <= 0)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new hasWrong(404,"注册失败，出现未知错误");
        }
        if (mapper.addStudentInfo(user.getId(), name, grade, sex, email) <= 0)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new hasWrong(404,"学生信息插入失败，出现未知错误");
        }
        mapper.deleteCode(email);
        return new hasWrong(200,"注册成功,快去登录吧！");
    }
}
