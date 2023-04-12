package com.app.commerce.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageResponse<T> {
    private int pageNum;
    private int pageSize;
    private long total;
    private Collection<T> data;

    public PageResponse(Page<T> page) {
        this.pageNum = page.getNumber();
        this.pageSize = page.getSize();
        this.total = page.getTotalElements();
    }

}
