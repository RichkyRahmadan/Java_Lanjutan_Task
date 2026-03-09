package com.tugas.sales_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_sales")
public class SalesEntity {
    @Id
    private Integer idSales;
    private Integer idItem;
    private Integer stokSales;
}
