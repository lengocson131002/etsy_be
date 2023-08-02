package com.app.commerce.dto.dashboard.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProfileStatusResponse {
    private Integer logoutCount;
    private Integer failedProxyCount;
    private Integer deletedCount;
    private Integer tooManyRequestCount;
    private Integer emptyCount;
    private Integer syncCount;

}
