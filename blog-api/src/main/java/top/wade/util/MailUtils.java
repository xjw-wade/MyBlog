package top.wade.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/7/18 22:27
 * @Description: 邮件工具类
 */
@EnableAsync
@Component
public class MailUtils {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    TemplateEngine templateEngine;

    @Async
    public void sendHtmlTemplateMail(Map<String, Object> map, String toAccount, String subject, String template) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            Context context = new Context();
            context.setVariables(map);
            String process = templateEngine.process(template, context);
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(toAccount);
            messageHelper.setSubject(subject);
            messageHelper.setText(process, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
