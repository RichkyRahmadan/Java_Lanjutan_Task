package com.tugas.item_service.service.serviceimpl;

import com.tugas.item_service.entity.ItemEntity;
import com.tugas.item_service.payload.req.ItemPayloadReq;
import com.tugas.item_service.payload.res.ItemPayloadRes;
import com.tugas.item_service.repository.ItemRepository;
import com.tugas.item_service.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    public ItemPayloadRes getDataItem(ItemPayloadReq payload) throws Exception {
        ItemPayloadRes res = new ItemPayloadRes();

        try {
            ItemEntity ent = itemRepository.getItemById(payload.getIdItemReq());
            if (ent == null) {
                throw new Exception("Id barang tidak ditemukan");
            } else {
                res.setIdItemRes(ent.getIdItem());
                res.setNameItemRes(ent.getNameItem());
                res.setStokItemRes(ent.getStokItem());
            }
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    public List<ItemPayloadRes> getAllDataItem() {
        List<ItemEntity> entities = itemRepository.getAllItem();

        return entities.stream().map(entity -> {
            ItemPayloadRes res = new ItemPayloadRes();
            res.setIdItemRes(entity.getIdItem());
            res.setNameItemRes(entity.getNameItem());
            res.setStokItemRes(entity.getStokItem());
            return res;
        }).collect(Collectors.toList());
    }

    @Override
    public void insertDataItem(ItemPayloadReq payload) throws Exception {
        try {
            ItemEntity entity = new ItemEntity();
            entity.setIdItem(payload.getIdItemReq());
            entity.setNameItem(payload.getNameItemReq());
            entity.setStokItem(payload.getStokItemReq());
            itemRepository.save(entity);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    @Override
    public void updateDataItem(ItemPayloadReq payload) throws Exception {
        try {
            ItemEntity oldEntity = itemRepository.getItemById(payload.getIdItemReq());
            oldEntity.setNameItem(payload.getNameItemReq());
            oldEntity.setStokItem(payload.getStokItemReq());
            itemRepository.save(oldEntity);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    @Override
    public void deleteDataItem(ItemPayloadReq payload) throws Exception {
        try {
            ItemEntity oldEntity = itemRepository.getItemById(payload.getIdItemReq());
            itemRepository.delete(oldEntity);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    @Override
    public void updateStokItem(Integer id, Integer stok) throws Exception {
        // TODO Auto-generated method stub
        try {
            ItemEntity oldEntity = itemRepository.getItemById(id);
            Integer oldStok = oldEntity.getStokItem();
            if (stok < 1) {
                throw new Exception("Stok Barang yang dipesan tidak valid");
            } else if (stok > oldEntity.getStokItem()) {
                throw new Exception("Stok Barang yang dibeli tidak mencukupi");
            } else {
                oldEntity.setStokItem(oldStok - stok);
            }
            itemRepository.save(oldEntity);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
}