package com.shoponline.model.dto;

import com.shoponline.model.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Damian on 2017-03-12.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String referenceNumber;

    private Integer quantity;

    private BigDecimal price;

    private String title;

    private String superAdminKey;

    private String imageSource;

    private String encodedImage;

    private CurrencyType currency;

}
