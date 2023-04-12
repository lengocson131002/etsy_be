package com.app.commerce.dto.shop.request;

import com.app.commerce.annotations.ExtractDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    @JsonProperty("title")
    private String title;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("status")
    private String status;

    @JsonProperty("stock")
    private int stock;

    @JsonProperty("price")
    private List<String> price;
}
