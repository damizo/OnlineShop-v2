package com.shoponline.model.dto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Damian on 2017-02-10.
 */
@Data
public class ProductCriteriaDTO implements Serializable{

    private Boolean priceSorting;

    private Boolean ratingSorting;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    private String title;

}
