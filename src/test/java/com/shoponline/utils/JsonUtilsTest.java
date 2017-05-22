package com.shoponline.utils;

import com.shoponline.model.dto.ProductCriteriaDTO;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * Created by Damian on 2017-03-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class JsonUtilsTest {

    @Test
    public void shouldDecodeOrEncodeDTO(){
        ProductCriteriaDTO beforeEncodeDTO = getProductCriteriaDTO();
        String encodedJson = JsonUtils.encodeBase64(beforeEncodeDTO);

        ProductCriteriaDTO afterDecodeDTO = JsonUtils.decodeBase64(encodedJson);

        Assert.assertEquals(beforeEncodeDTO,afterDecodeDTO);
    }


    private ProductCriteriaDTO getProductCriteriaDTO() {
        ProductCriteriaDTO beforeEncodeDTO = new ProductCriteriaDTO();
        beforeEncodeDTO.setTitle("Test Product");
        beforeEncodeDTO.setPriceTo(new BigDecimal(1000));
        return beforeEncodeDTO;
    }

}
