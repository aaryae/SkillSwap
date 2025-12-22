package org.example.authservice.service;


import org.example.authservice.model.User;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendPasswordReset(User user);
    boolean verify(String code, User user);
     void sendVerification(User user, String content,String subject);
    void sendWelcomeMail(User user);
    void sendPasswordAboutToExpire(User user);
}
