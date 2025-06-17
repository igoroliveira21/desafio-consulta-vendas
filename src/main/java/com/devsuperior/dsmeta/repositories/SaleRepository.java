package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projection.SaleProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name " +
            "FROM tb_sales INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date BETWEEN :minDate and :maxDate " +
            "AND UPPER(tb_saller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    List<SaleProjection> searchSale(LocalDate minDate, LocalDate maxDate, String name);
}
