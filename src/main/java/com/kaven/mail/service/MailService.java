package com.kaven.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * @program: mail
 * @description:
 * @author: Kaven
 * @create: 2018-10-26 19:45
 **/
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String to,String subject,
                               String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        mailSender.send(message);
    }

    public void sendHtmlMail(String to,String subject,
                             String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message , true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content , true);
        helper.setFrom(from);

        mailSender.send(message);
    }

    public void sendAttachmentsMail(String to, String subject,
                                    String content , List<String> filepaths) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message , true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content , true);
        helper.setFrom(from);

        for(String filepath : filepaths){
            FileSystemResource file = new FileSystemResource(new File(filepath));
            String filename = file.getFilename();

            helper.addAttachment(filename , file);
        }

        mailSender.send(message);
    }

    public void sendInlineResourceMail(String to, String subject,
                                      String content , String rscPath,
                                      String rscId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message , true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content , true);
        helper.setFrom(from);

        FileSystemResource resource = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId , resource);
        mailSender.send(message);
    }
}
