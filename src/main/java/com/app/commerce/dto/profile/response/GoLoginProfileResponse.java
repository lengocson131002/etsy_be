package com.app.commerce.dto.profile.response;

import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GoLoginProfileResponse {

    private Long id;

    private String goLoginProfileId;

    private String name;

    private String notes;

    private OffsetDateTime createdDate;

    private String proxy;

    private String folderName;

    private String shopId;

    private String shopName;

    private List<String> status;

}
