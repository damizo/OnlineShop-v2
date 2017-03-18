package com.shoponline.model.dto;

import com.shoponline.model.entity.Product;
import com.sun.org.apache.xpath.internal.operations.Equals;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Damian on 2017-03-12.
 */
public class ProductDTO {
    private String referenceNumber;

    private Integer quantity;

    private BigDecimal price;

    private String title;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   public static class ProductDTOBuilder {
        private ProductDTO productDTO = null;

        public ProductDTOBuilder(){
            productDTO = new ProductDTO();
        }

        public ProductDTOBuilder referenceNumber(String referenceNumber){
            productDTO.setReferenceNumber(referenceNumber);
            return this;
        }

       public ProductDTOBuilder quantity(Integer quantity){
           productDTO.setQuantity(quantity);
           return this;
       }

       public ProductDTOBuilder price(BigDecimal price){
           productDTO.setPrice(price);
           return this;
       }

       public ProductDTOBuilder title(String title){
           productDTO.setReferenceNumber(title);
           return this;
       }

       public ProductDTO build(){
           return productDTO;
       }
    }

    @Override
    public int hashCode(){
        return Objects.hash(referenceNumber, quantity, price, title);
    }

    @Override
    public boolean equals(Object o){
        return EqualsBuilder.reflectionEquals(this, o);
    }
}
