package com.tugas.item_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugas.item_service.payload.req.ItemPayloadReq;
import com.tugas.item_service.payload.res.ItemPayloadRes;
import com.tugas.item_service.service.ItemService;
import com.tugas.item_service.utility.Message;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/getAllItem")
    public ResponseEntity getAllItem() {
        try {
            List<ItemPayloadRes> result = itemService.getAllDataItem();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Terjadi Error: " + e.getMessage());
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    @GetMapping("/getItem")
    public ResponseEntity getItem(@RequestBody ItemPayloadReq payload) {
        try {
            ItemPayloadRes result = itemService.getDataItem(payload);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Terjadi Error: " + e.getMessage());
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    @GetMapping("/getItemByParam")
    public ResponseEntity getItem(@RequestParam("idItem") Integer id) {
        try {
            ItemPayloadReq payload = new ItemPayloadReq();
            payload.setIdItemReq(id);
            ItemPayloadRes result = itemService.getDataItem(payload);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Terjadi Error: " + e.getMessage());
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    @PostMapping("/insertItem")
    public ResponseEntity insertItem(@RequestBody ItemPayloadReq payload){
        //TODO: process POST request
        try {
            itemService.insertDataItem(payload);
            return new ResponseEntity<>("Insert Data Item Success!",HttpStatus.OK);
            } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateItem")
    public ResponseEntity updateItem(@RequestBody ItemPayloadReq payload){
        //TODO: process POST request
        try {
            itemService.updateDataItem(payload);
            return new ResponseEntity<>("Update Data Item Success!",HttpStatus.OK);
            } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateStokItem")
    public ResponseEntity updateStokItem(@RequestParam("id") Integer id,@RequestParam("stok") Integer stok){
        //TODO: process POST request
        try {
            itemService.updateStokItem(id,stok);
            return new ResponseEntity<>("Update stok Item Success!",HttpStatus.OK);
            } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteItem")
    public ResponseEntity deleteItem(@RequestBody ItemPayloadReq payload){
        //TODO: process POST request
        try {
            itemService.deleteDataItem(payload);
            return new ResponseEntity<>("Delete Data Item Success!",HttpStatus.OK);
            } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}
