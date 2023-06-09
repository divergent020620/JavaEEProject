package com.example.api.service.impl;
//
//import com.example.api.model.entity.Code;
//import com.example.api.repository.CodeRepository;
//import com.example.api.service.EmailService;
//import com.example.api.utils.RandomUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//
//    @Resource
//    private CodeRepository codeRepository;
//
//    @Resource
//    private JavaMailSender mailSender;
//
//    @Value("${spring.mail.username}")
//    private String from;
//
//    @Override
//    public void sendVerificationCode(String email) throws MailException {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(email);
//        message.setSubject("验证码");
//        String value = RandomUtil.next();
//        message.setText("你的验证码为:  " + value + "  十五分钟内有效");
//        mailSender.send(message);
//        //保存验证码
//        //同一主键的email为update操作
//        codeRepository.save(new Code(email, value));
//    }
//
//    @Override
//    public boolean checkVerificationCode(String email, String value) {
//        Code code = codeRepository.findByEmailAndValue(email, value);
//        if (code != null && code.getExp() > System.currentTimeMillis()) {
//            //登陆成功删除验证码
//            codeRepository.delete(code);
//            return true;
//        }
//        return false;
//    }
//
//}

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.api.model.entity.Code;
import com.example.api.repository.CodeRepository;
import com.example.api.service.EmailService;
import com.example.api.utils.RandomUtil;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private CodeRepository codeRepository;

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private static final String EMAIL_SERVICE = "emailService";

    @CircuitBreaker(name = EMAIL_SERVICE, fallbackMethod = "sendVerificationCodeFallback")
    @Override
    public void sendVerificationCode(String email) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("验证码");
        String value = RandomUtil.next();
        message.setText("你的验证码为:  " + value + "  十五分钟内有效");
        mailSender.send(message);
        //保存验证码
        //同一主键的email为update操作
        codeRepository.save(new Code(email, value));
    }


    @Override
    public void sendVerificationCodeFallback(String email, Exception e) {
        // handle fallback behavior, for example:
        System.out.println("Fallback for sendVerificationCode() method: " + e.getMessage());
    }

    private static final String EMAIL_SERVICE2 = "emailService2";

    @CircuitBreaker(name = EMAIL_SERVICE2, fallbackMethod = "checkVerificationCodeFallback")
    @Override
    public boolean checkVerificationCode(String email, String value) {
        Code code = codeRepository.findByEmailAndValue(email, value);
        if (code != null && code.getExp() > System.currentTimeMillis()) {
            //登陆成功删除验证码
            codeRepository.delete(code);
            return true;
        }
        return false;
    }


    @Override
    public boolean checkVerificationCodeFallback(String email, String value, Exception e) {
        // handle fallback behavior, for example:
        System.out.println("Fallback for checkVerificationCode() method: " + e.getMessage());
        return false;
    }
}
