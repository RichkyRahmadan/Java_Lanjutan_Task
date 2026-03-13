package com.tugas.item_service.service.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tugas.item_service.repository.ItemRepository;
import com.tugas.item_service.service.EmailService;

import jakarta.mail.internet.MimeMessage;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    JavaMailSender mailSender;

    @Value("${email.sender}")
    private String senderEmail;

    @Override
    public void sendEmailAllItem() throws Exception {
        try {
            Context context = new Context();
            context.setVariable("judulEmail", "Laporan Stok Barang");
            context.setVariable("listProduk", itemRepository.getAllItem());

            String htmlFinal = templateEngine.process("email-template", context);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(senderEmail);
            messageHelper.setTo(senderEmail);
            messageHelper.setSubject("Chyone Store Mailing System - " + formattedDateTime + " - Stock Item Currently");
            messageHelper.setText(htmlFinal, true);

            mailSender.send(message);

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
