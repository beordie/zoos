package com.beordie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beordie.model.entity.Animal;
import com.beordie.model.factory.AnimalFactory;
import com.beordie.service.IEmailService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/3 17:17
 * @User beordie
 */
public class HtmlEmailService extends IEmailService {

    private Cache<String, String> codeCache = null;

    @Override
    public boolean sendEmail(String fromEmail, String toEmail, String message) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setText(message, true);
        helper.setSubject("验证码");
        mailSender.send(mailMessage);
        return true;
    }

    @Override
    public String sendCode(String email) {
        String code = new Random().nextInt(900000) + 100000 + "";
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html> <html> <head> <meta charset=\"utf-8\"> <title></title> </head> <body> <div> <includetail> <div align=\"center\"> <div class=\"open_email\" style=\"margin-left: 8px; margin-top: 8px; margin-bottom: 8px; margin-right: 8px;\"> <div> <br> <span class=\"genEmailContent\"> <div id=\"cTMail-Wrap\" style=\"word-break: break-all;box-sizing:border-box;text-align:center;min-width:320px; max-width:660px; border:1px solid #f6f6f6; background-color:#f7f8fa; margin:auto; padding:0 0 30px; font-family:'helvetica neue',PingFangSC-Light,arial,'hiragino sans gb','microsoft yahei ui','microsoft yahei',simsun,sans-serif\"> \n" +
                "<div class=\"main-content\" style=\"\"> <table style=\"width:100%;font-weight:300;margin-bottom:10px;border-collapse:collapse\"> <tbody> <tr style=\"font-weight:300\"> <td style=\"width:3%;max-width:30px;\"></td> <td style=\"max-width:600px;\"> <div id=\"cTMail-logo\" style=\"width:92px; height:25px;\"> </div> <p style=\"height:2px;background-color: #00a4ff;border: 0;font-size:0;padding:0;width:100%;margin-top:20px;\"></p> <div id=\"cTMail-inner\" style=\"background-color:#fff; padding:23px 0 20px;box-shadow: 0px 1px 1px 0px rgba(122, 55, 55, 0.2);text-align:left;\"> <table style=\"width:100%;font-weight:300;margin-bottom:10px;border-collapse:collapse;text-align:left;\"> \n" +
                "<tbody> <tr style=\"font-weight:300\"> <td style=\"width:3.2%;max-width:30px;\"></td> <td style=\"max-width:480px;text-align:left;\"> <h1 id=\"cTMail-title\" style=\"font-size: 20px; line-height: 36px; margin: 0px 0px 22px;\"> 【欢迎注册】 </h1> <p id=\"cTMail-userName\" style=\"font-size:16px;color:#333; line-height:24px; margin:0;\"> 您好！ </p> <p>&emsp;&emsp;验证码的有效时间为 <b>30</b> 分钟。</p> <p>&emsp;&emsp;请在规定时间内完成注册。</p> <p style=\"font-weight: bold;\">&emsp;&emsp;非本人操作可忽略。</p> <p class=\"cTMail-content\" style=\"font-size: 14px; color: rgb(51, 51, 51); line-height: 24px; margin: 6px 0px 0px; word-wrap: break-word; word-break: break-all;\"> \n" +
                "<a id=\"cTMail-btn\" title=\"\" style=\"font-size: 16px; line-height: 45px; display: block; background-color: rgb(0, 164, 255); color: rgb(255, 255, 255); text-align: center; text-decoration: none; margin-top: 20px; border-radius: 3px;\">");
        builder.append(code);
        builder.append("</a> </p> <dl style=\"font-size: 14px; color: rgb(51, 51, 51); line-height: 18px;\"> <dd style=\"margin: 0px 0px 6px; padding: 0px; font-size: 12px; line-height: 22px;\"> <p id=\"cTMail-sender\" style=\"font-size: 14px; line-height: 26px; word-wrap: break-word; word-break: break-all; margin-top: 32px;\"> 此致 <br> <strong>&emsp;Be Or Die</strong> </p> </dd> </dl> </td> <td style=\"width:3.2%;max-width:30px;\"></td> </tr> \n" +
                "</tbody> </table> </div> <div id=\"cTMail-copy\" style=\"text-align:center; font-size:12px; line-height:18px; color:#999\"> <table style=\"width:100%;font-weight:300;margin-bottom:10px;border-collapse:collapse\"> <tbody> <tr style=\"font-weight:300\"> <td style=\"width:3.2%;max-width:30px;\"></td> <td style=\"max-width:540px;\"> <p id=\"cTMail-rights\" style=\"max-width: 100%; margin:auto;font-size:12px;color:#999;text-align:center;line-height:22px;\"> " +
                "<div style=\"height: 10px;\"></div><img border=\"0\" src=\"http://express.beordie.cn/images/qrcode.png\" style=\"width:auto; height:100px; margin:0 auto;\"> " +
                "</p> </td> <td style=\"width:3.2%;max-width:30px;\"></td> </tr> </tbody> </table> </div> </td> <td style=\"width:3%;max-width:30px;\"></td> </tr> </tbody> </table> </div> </div> </span> <br> </div> </div> </div> </includetail> </div> </body> </html> ");
        String content = builder.toString();
        try {
            boolean message = sendEmail(serverEmail, email, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        codeCache.put(email, code);
        return code;
    }

    @Override
    public String getCode(String email) {
        return this.codeCache.get(email, new Function<String, String>() {
            @Override
            public String apply(String email) {
                return null;
            }
        });
    }

    public HtmlEmailService() {
        this.codeCache = Caffeine.newBuilder()
                .initialCapacity(1000)
                .maximumSize(10000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build();;
    }
}
