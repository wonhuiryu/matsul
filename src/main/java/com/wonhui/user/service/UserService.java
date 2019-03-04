package com.wonhui.user.service;

import com.wonhui.entity.User;
import com.wonhui.user.repository.UserRepository;
import com.wonhui.util.AES256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService {

    @Autowired
    private AES256Util aes256Util;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void sendMail(User user) throws GeneralSecurityException, UnsupportedEncodingException {

        //google smtp account
        final String username = "dbdnjsgml18@gmail.com";
        final String password = "1718toRl!";

        //server ip:port(추후)
        final String server = "localhost:8080/users/email/";

        //CRUDRepository 추후 API 제공 안될 시, 직접 업데이트문 작성
/*        Optional<User> userOptional = userRepository.findById(user.getEmail());
        user = userOptional.orElse(user);*/
/*        User userDto = userRepository.findById(user.getEmail()).get();
        user = Optional.ofNullable(userDto).orElse(user);*/

        //암호화 키(이메일 기반 암호화)
        String signUpKey = aes256Util.encrypt(user.getEmail());
        user.setSignUpKey(signUpKey);
        userRepository.save(user);


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            //보낸 유저 이메일
            message.setFrom(new InternetAddress("dbdnjsgml18@gmail.com"));
            //보낼 유저 이메일
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            //제목
            message.setSubject("[matsul] verify yout e-mail, and join us right now!");
            //내용
/*            message.setContent("click here and join us with setting your password!"
                    + "\n\n <a href=\"localhost:8080/users/email/"
                    +signUpKey
                    +"\"></a>", "text/html; charset=utf-8");*/

            String message1 = "<a href=\"localhost:8080/users/email/"+signUpKey+"\">Join Us!</a>";
            message.setContent(message1, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
