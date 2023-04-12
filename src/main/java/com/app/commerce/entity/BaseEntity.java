package com.app.commerce.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private OffsetDateTime createdAt;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private OffsetDateTime updatedAt;
    private String deletedBy;
    private OffsetDateTime deletedAt;
}
