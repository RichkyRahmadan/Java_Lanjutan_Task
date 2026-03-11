package com.tugas.item_service.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendEmailAllItem() throws Exception;

    public void sendEmailAllItemScheduling() throws Exception;
}
