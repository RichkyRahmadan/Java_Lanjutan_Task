package com.tugas.sales_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tugas.sales_service.entity.SalesEntity;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, Long>{
    @Query(value = "select * from tbl_sales where id_sales = ?1", nativeQuery = true)
    SalesEntity getSalesById(Integer idSales);

}
