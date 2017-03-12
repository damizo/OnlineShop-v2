package com.shoponline.model.dto;

import java.math.BigDecimal;

/**
 * Created by Damian on 2017-02-10.
 */
public class ProductCriteriaDTO {

    private Boolean priceSorting;

    private Boolean ratingSorting;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    private String title;

    public Boolean getPriceSorting() {
        return priceSorting;
    }

    public void setPriceSorting(Boolean priceSorting) {
        if(priceSorting == null){
            priceSorting = Boolean.FALSE;
        }
        this.priceSorting = priceSorting;
    }

    public Boolean getRatingSorting() {
        if(ratingSorting == null){
            ratingSorting = Boolean.FALSE;
        }
        return ratingSorting;
    }

    public void setRatingSorting(Boolean ratingSorting) {
        this.ratingSorting = ratingSorting;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
