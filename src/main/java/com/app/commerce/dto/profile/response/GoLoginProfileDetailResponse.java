package com.app.commerce.dto.profile.response;

import com.app.commerce.dto.shop.response.ShopResponse;
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
public class GoLoginProfileDetailResponse {

    public final static String COLLECTION_NAME = "go_login_profiles";

    private Long id;

    private String goLoginProfileId;

    private String name;

    private String notes;

    private OffsetDateTime createdDate;

    private String proxy;

    private String folderName;

    private List<ShopResponse> shops = new ArrayList<>();

    private List<String> status;

}
