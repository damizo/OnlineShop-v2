package com.shoponline.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dami on 2016-12-11.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    public Long getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    @PrePersist
    private void setCreateDate(){
        createDate = new Date();
    }

    @PreUpdate
    private void setModifyDateDate(){
        modifyDate = new Date();
    }
}
