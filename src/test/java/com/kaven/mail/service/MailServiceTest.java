package com.kaven.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mail
 * @description: MailService 测试类
 * @author: Kaven
 * @create: 2018-10-26 20:01
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("1433188757@qq.com" , "第一封文本邮件" ,
                "这是我的第一封文本邮件");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {

        String content = "<html>\n" +
                              "<body>\n"+
                                   "<h1> 这是我的第一封 HTML 邮件！</h1>\n" +
                              "</body>\n" +
                          "</html>";
        mailService.sendHtmlMail("1433188757@qq.com" , "第一封 HTML 邮件" ,
                content);
    }

    @Test
    public void sendAttachmentsMailTest() throws MessagingException {

        String filepath = "E:\\study\\SpringBootMail\\kaven.docx";
        List<String> list = new ArrayList<String>();
        list.add(filepath);
//        FileSystemResource fileSystemResource = new FileSystemResource(new File(filepath));
//        System.out.println(fileSystemResource.getFilename());
        mailService.sendAttachmentsMail("1872645313@qq.com" , "第一封带附件邮件" ,
                "这是我的第一封带附件邮件" ,list);
    }

    @Test
    public void sendInlineResourceMailTest() throws MessagingException {

        String imgPath = "E:\\study\\SpringBootMail\\1.jpg";
        String rscId = "1";
        String content = "<html><body> 第一封图片邮件 <img src=\'cid:"+ rscId +
                "\'></img></body></html>";
        mailService.sendInlineResourceMail("1872645313@qq.com" , "第一封图片邮件" ,
                content ,imgPath ,rscId);
    }
}
