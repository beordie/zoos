package com.beordie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/3 17:19
 * @User beordie
 */
public abstract class IEmailService implements IMessageService{
    /**
     * spring boot封装的邮箱操作
     */
    protected JavaMailSenderImpl mailSender;

    /**
     * 发送方邮箱
     */
    @Value("${spring.mail.username}")
    protected String serverEmail = "3050066014@qq.com";

    /**
     * smtp 服务主机
     */
    @Value("${spring.mail.host}")
    protected String serverHost = "smtp.qq.com";

    /**
     * smtp 协议
     */
    @Value("${spring.mail.protocol}")
    protected String protocol = "smtp";

    /**
     * 授权码
     */
    @Value("${spring.mail.password}")
    protected String password = "zqkbkxwzyhupdhbi";

    /**
     * 默认编码
     */
    @Value("${spring.mail.default-encoding}")
    protected String defaultEncoding = "UTF-8";

    /**
     * 默认编码
     */
    @Value("${spring.mail.port}")
    protected Integer serverPort = 25;

    /**
     * 无参构造, 进行邮件相关信息的设置
     */
    public IEmailService() {
        init();
    }

    @Override
    public void init() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.serverHost);
        mailSender.setDefaultEncoding(this.defaultEncoding);
        mailSender.setPassword(this.password);
        mailSender.setProtocol(this.protocol);
        mailSender.setUsername(this.serverEmail);
        mailSender.setPort(serverPort);
    }

    /**
     * 邮件发送
     * @param fromEmail
     * @param toEmail 接收方
     * @param message 邮件信息
     * @return
     * @throws MessagingException
     */
    protected abstract boolean sendEmail(String fromEmail, String toEmail, String message) throws MessagingException;

    public abstract String sendCode(String email);

    public abstract String getCode(String email);
}
