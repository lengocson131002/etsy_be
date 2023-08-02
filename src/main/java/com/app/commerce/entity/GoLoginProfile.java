package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goLoginProfileId;

    private String name;

    private String notes;

    private OffsetDateTime createdDate;

    private String proxy;

    private String folderName;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Shop> shops;

    private Boolean isLogOut;

    private Boolean isFailedProxy;

    private Boolean isDeleted;

    private Boolean isTooManyRequest;

    private Boolean isEmpty;
}
