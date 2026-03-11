package com.tugas.item_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tugas.item_service.service.EmailService;
import com.tugas.item_service.utility.Message;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;

    @GetMapping("/getAllItemToEmail")
    public ResponseEntity getAllItemToEmail() {
        try {
            emailService.sendEmailAllItem();
            return new ResponseEntity("Email Has Been Sent!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Terjadi Error: " + e.getMessage());
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

}
