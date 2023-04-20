package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = GoLoginProfile.COLLECTION_NAME)
@FieldNameConstants
public class GoLoginProfile {

    public final static String COLLECTION_NAME = "go_login_profiles";

    @Id
    private String id;

    private String name;

    private String notes;

    private OffsetDateTime createdDate;

    private String proxy;

    private String folderName;

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
