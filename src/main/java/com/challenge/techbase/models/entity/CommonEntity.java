package com.challenge.techbase.models.entity;

import com.challenge.techbase.util.Enum.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class CommonEntity {

    private Integer createdBy;
    private Date createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;
    private Status status = Status.ACTIVE;

    @PrePersist
    protected void prePersist() {
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.modifiedDate = new Date();
    }
}
