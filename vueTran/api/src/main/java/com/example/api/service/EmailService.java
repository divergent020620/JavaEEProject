package com.example.api.service;

import org.springframework.mail.MailException;

public interface EmailService {

    //发送验证码
    void sendVerificationCode(String email) throws MailException;
    void sendVerificationCodeFallback(String email, Exception e);
    //检验验证码
    boolean checkVerificationCode(String email, String code);
    boolean checkVerificationCodeFallback(String email, String code, Exception e);

}
