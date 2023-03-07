package com.example.demo.util;

import org.springframework.stereotype.Component;

public class MailSenderStub implements MailSender{
    @Override
    public boolean send() {
        return true;
    }
}
