package com.tugas.sales_service.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tugas.sales_service.entity.SalesEntity;
import com.tugas.sales_service.payload.req.SalesPayloadReq;
import com.tugas.sales_service.payload.res.ItemPayloadRes;
import com.tugas.sales_service.payload.res.SalesPayloadRes;
import com.tugas.sales_service.repository.SalesRepository;
import com.tugas.sales_service.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    SalesRepository salesRepository;

    public SalesPayloadRes getDataSales(SalesPayloadReq payload) throws Exception {
        SalesPayloadRes res = new SalesPayloadRes();
        try {
            SalesEntity entity = salesRepository.getSalesById(payload.getIdSalesReq());
            if (entity == null) {
                throw new Exception("Id Penjualan tidak ditemukan");
            } else {
                res.setIdSalesRes(entity.getIdSales());
                res.setIdItemRes(entity.getIdItem());
                res.setStokSalesRes(entity.getStokSales());
            }
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    // public ItemPayloadRes getStokItem(SalesPayloadReq payload, WebClient
    // webClient) throws Exception{

    // SalesPayloadRes entity = getDataSales(payload);
    // int req = entity.getIdItemRes();
    // ItemPayloadRes result = webClient.get()
    // .uri("/item/getItemByParam?idItem={req}",req)
    // .retrieve()
    // .bodyToMono(ItemPayloadRes.class)
    // .block();

    // result.setStokItemRes(result.getStokItemRes() - entity.getStokSalesRes());
    // return result;
    // }

    @Override
    public void insertSales(SalesPayloadReq payload, WebClient webClient) throws Exception {
        try {
            SalesEntity entity = new SalesEntity();
            Integer idReq = payload.getIdItemReq();
            Integer stokReq = payload.getStokSalesReq();
            webClient.put()
                    .uri("/item/updateStokItem?id={idReq}&stok={stokReq}", idReq, stokReq)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            entity.setIdSales(payload.getIdSalesReq());
            entity.setIdItem(payload.getIdItemReq());
            entity.setStokSales(payload.getStokSalesReq());
            salesRepository.save(entity);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

}
