package com.shoponline.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shoponline.model.enums.CurrencyType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Damian on 2017-02-10.
 */
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "title")
    private String title;

    @Column(name = "image_source")
    private String imageSource;

    @ElementCollection(targetClass = CurrencyType.class)
    @CollectionTable(name = "product_currency", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<CurrencyType> currencyTypes = Collections.emptySet();

    public Product(){

    }

    public Product(final String referenceNumber, final BigDecimal price, final String title, final Integer quantity) {
        this.referenceNumber = referenceNumber;
        this.price = price;
        this.title = title;
        this.quantity = quantity;
    }

    public Product(final String referenceNumber, final BigDecimal price, final String title, final List<Order> orders) {
        this.referenceNumber = referenceNumber;
        this.price = price;
        this.title = title;
        this.orders = orders;
    }

    public Product(final String referenceNumber, final Integer quantity, final BigDecimal price, final String title, final List<Order> orders) {
        this.referenceNumber = referenceNumber;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.orders = orders;
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

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Set<CurrencyType> getCurrencyTypes() {
        if(currencyTypes == null || currencyTypes.isEmpty()) {
        currencyTypes = Stream.of(CurrencyType.values()).collect(Collectors.toSet());
        }
        return currencyTypes;
    }

    public void setCurrencyTypes(Set<CurrencyType> currencyTypes) {
        this.currencyTypes = currencyTypes;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public CurrencyType getMainCurrency() {
        return CurrencyType.PLN;
    }
}
