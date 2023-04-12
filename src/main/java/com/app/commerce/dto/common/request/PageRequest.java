package com.app.commerce.dto.common.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    @NotNull
    @PositiveOrZero
    private int pageNum;
    @NotNull
    @Positive
    @Max(100)
    private int pageSize;
}
