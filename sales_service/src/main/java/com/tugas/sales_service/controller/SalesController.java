package com.tugas.sales_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.tugas.sales_service.payload.req.SalesPayloadReq;
import com.tugas.sales_service.payload.res.ItemPayloadRes;
import com.tugas.sales_service.payload.res.SalesPayloadRes;
import com.tugas.sales_service.service.SalesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/sales")
public class SalesController {
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090").build();

    @Autowired
    SalesService salesService;

    // @GetMapping("/getStokItem")
    // public ResponseEntity getStokItem(@RequestParam("idSales") Integer idSales){
    // try {
    // SalesPayloadReq payload = new SalesPayloadReq();
    // payload.setIdSalesReq(idSales);

    // ItemPayloadRes result = salesService.getStokItem(payload,webClient);
    // return new ResponseEntity(result,HttpStatus.OK);
    // } catch (Exception e) {
    // System.out.println("Pesan error: "+e.getMessage());
    // System.out.println("Penyebab error: "+e.getCause());
    // if(e.getCause() != null && e.getCause().toString().contains("Connection
    // refused")){
    // return new ResponseEntity("koneksi ke service
    // gagal",HttpStatus.SERVICE_UNAVAILABLE);
    // }
    // return new ResponseEntity("Gagal karena lain hal", HttpStatus.NOT_FOUND);
    // }
    // }

    @PostMapping("/insertSales")
    public ResponseEntity insertSales(@RequestBody SalesPayloadReq payload) {
        // TODO: process POST request
        try {
            salesService.insertSales(payload, webClient);
            return new ResponseEntity<>("Insert Data Success!", HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
