package com.nys.bookstore.utils;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.nys.bookstore.user.domain.User;
import com.sun.mail.util.MailSSLSocketFactory;

public class EmailUtil {
    public static void sendEmail(User user) throws GeneralSecurityException
    {
        // 收件人电子邮箱
        String to = user.getEmail();

        // 发件人电子邮箱
        String from = "928190869@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, "yrqyubrycrjybdbc"); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("网上书店账号激活！！！！");

            // 设置消息体
            message.setText("请点击下面链接激活账号，或复制该网址并访问\n" +
                    "http://192.168.20.106:8080/BookStore/user?method=active&code="+user.getCode());

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from runoob.com");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static void sendHTMLEmail(User user){


        // 收件人电子邮箱
        String to = user.getEmail();

        // 发件人电子邮箱
        String from = "928190869@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        // 获取默认的 Session 对象。
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, "yrqyubrycrjybdbc"); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象。
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头字段
            message.setSubject("网上书店账号激活！！！！");

            String url = "http://192.168.20.106:8080/BookStore/user?method=active&code="+user.getCode();
            // 发送 HTML 消息, 可以插入html标签
            message.setContent("<html><head ><meta charset='utf-8'>" +
                            "</head><body><h1>请点击下面链接激活账号，或复制该网址并访问</h1>" +
                            "<a href=‘"+url+"’>"+url+"</a></body></html>",
                    "text/html;charset=utf-8" );

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}