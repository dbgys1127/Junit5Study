package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class MailSenderAdapter implements MailSender {
//    private Mail mail;
//
//    public MailSenderAdapter(Mail mail) {
//        this.mail = mail;
//    }

    @Override
    public boolean send() {
        return true;
    }
}
