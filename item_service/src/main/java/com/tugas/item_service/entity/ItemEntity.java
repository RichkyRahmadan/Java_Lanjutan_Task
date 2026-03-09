package com.tugas.item_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "tbl_item")
public class ItemEntity {
    @Id
    private Integer idItem;
    private Integer stokItem;
    private String nameItem;
}
