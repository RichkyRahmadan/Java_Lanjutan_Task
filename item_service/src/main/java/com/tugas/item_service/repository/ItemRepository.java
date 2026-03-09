package com.tugas.item_service.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tugas.item_service.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    @Query(value = "select * from tbl_item where id_item = ?1", nativeQuery = true)
    ItemEntity getItemById(Integer idItem);
    
    @Query(value = "select * from tbl_item", nativeQuery = true)
    List<ItemEntity> getAllItem();
}
