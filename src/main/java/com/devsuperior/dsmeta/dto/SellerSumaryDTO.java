package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projection.SellerSumaryProjection;

import java.time.LocalDate;

public class SellerSumaryDTO {

    private String sellerName;
    private Double amount;

    public SellerSumaryDTO() {}

    public SellerSumaryDTO(String sellerName, Double amount) {
        this.sellerName = sellerName;
        this.amount = amount;
    }

    public SellerSumaryDTO(SellerSumaryProjection projection) {
        sellerName = projection.getName();
        amount = projection.getAmount();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
