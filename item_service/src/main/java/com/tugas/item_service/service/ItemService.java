package com.tugas.item_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tugas.item_service.payload.req.ItemPayloadReq;
import com.tugas.item_service.payload.res.ItemPayloadRes;

@Service
public interface ItemService {
    public ItemPayloadRes getDataItem (ItemPayloadReq payload) throws Exception;
    public List<ItemPayloadRes> getAllDataItem() throws Exception;
    public void insertDataItem(ItemPayloadReq payload) throws Exception;
    public void updateDataItem(ItemPayloadReq payload) throws Exception;
    public void updateStokItem(Integer id, Integer stok) throws Exception;
    public void deleteDataItem(ItemPayloadReq payload) throws Exception;
}
