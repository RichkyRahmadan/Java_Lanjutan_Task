package com.tugas.item_service.service.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tugas.item_service.repository.ItemRepository;
import com.tugas.item_service.service.EmailService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TemplateEngine templateEngine;


    @Value("${smtp.host}")
    private String smtpHost;
    @Value("${smtp.port}")
    private String smtpPort;
    @Value("${email.password}")
    private String senderPassword;
    @Value("${email.sender}")
    private String senderEmail;

    @Override
    public void sendEmailAllItem() throws Exception {
        try {
            InternetAddress[] senderEmailWithoutName = InternetAddress.parse(senderEmail);

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(senderEmailWithoutName[0].getAddress(),
                                    senderPassword);
                        }
                    });


            Context context = new Context();
            context.setVariable("judulEmail", "Laporan Stok Barang");
            context.setVariable("listProduk", itemRepository.getAllItem());

            String htmlFinal = templateEngine.process("email-template", context);
            try {
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);

                javax.mail.Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(javax.mail.Message.RecipientType.TO,
                        InternetAddress.parse(senderEmail));

                message.setSubject("Chyone Store Mailing System - " +
                        formattedDateTime + " - " + "Stock Item Currently");

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(htmlFinal, "text/html; charset=utf-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                message.setContent(multipart);
                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    @Override
    @Scheduled(cron = "0 0 6 * * ?")
    public void sendEmailAllItemScheduling() throws Exception {
        sendEmailAllItem();
        System.out.println("Daily Mail Has Been Sent");
    }

}
