package com.lawencon.admin.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.lawencon.admin.model.Email;

@Service
public class EmailService {
    
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    SpringTemplateEngine templateEngine;

    public void sendHtmlMessage(Email email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setFrom(email.getSenderEmail());
        helper.setTo(email.getRecipientEmail());
        helper.setSubject(email.getSubject());
        String html = templateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);
        emailSender.send(message);
    }
    public void sendHtmlMessageWithAttachment(Email email,String fileName, byte[] file) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setFrom(email.getSenderEmail());
        helper.setTo(email.getRecipientEmail());
        helper.setSubject(email.getSubject());
        ByteArrayResource files = new ByteArrayResource(file);
        helper.addAttachment(fileName, files);
        String html = templateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);
        emailSender.send(message);
    }
}
