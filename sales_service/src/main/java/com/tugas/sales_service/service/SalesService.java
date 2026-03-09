package com.tugas.sales_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tugas.sales_service.payload.req.SalesPayloadReq;
import com.tugas.sales_service.payload.res.ItemPayloadRes;
import com.tugas.sales_service.payload.res.SalesPayloadRes;

@Service
public interface SalesService {
    public SalesPayloadRes getDataSales(SalesPayloadReq payload) throws Exception;
    // public ItemPayloadRes getStokItem(SalesPayloadReq payload, WebClient webClient) throws Exception;
    public void insertSales(SalesPayloadReq payload, WebClient webClient) throws Exception;
}
